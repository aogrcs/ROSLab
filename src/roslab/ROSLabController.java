package roslab;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roslab.model.electronics.Circuit;
import roslab.model.general.Configuration;
import roslab.model.general.Endpoint;
import roslab.model.general.Library;
import roslab.model.general.Link;
import roslab.model.general.Node;
import roslab.model.mechanics.HWBlock;
import roslab.model.software.ROSMsgType;
import roslab.model.software.ROSNode;
import roslab.model.software.ROSPort;
import roslab.model.software.ROSTopic;
import roslab.model.ui.UIEndpoint;
import roslab.model.ui.UILink;
import roslab.model.ui.UINode;
import roslab.processors.general.ConfigurationParser;
import roslab.processors.general.LibraryParser;
import roslab.ui.general.NewLinkDialog;
import roslab.ui.general.NewNodeDialog;
import roslab.ui.general.ROSLabTree;
import roslab.ui.software.EditRateDialog;
import roslab.ui.software.LoadLibraryDialog;
import roslab.ui.software.NewCustomControllerDialog;
import roslab.ui.software.NewCustomPortDialog;
import roslab.ui.software.NewCustomTopicDialog;

public class ROSLabController implements Initializable {
    static Logger logger = LoggerFactory.getLogger(ROSLabController.class);

    // TODO Use this class for each tab contents (sw, hw, ee)
    class ModeContents {
        ROSLabTree tree;
        Library lib;
        Configuration config;
        ContextMenu menu;
        Group uiGroup;
    }

    @FXML
    TabPane mainTabPane;

    @FXML
    Tab swTab;

    @FXML
    TreeView<String> swTreeView;

    @FXML
    AnchorPane swPane;

    ROSLabTree swTree;
    Library swLibrary = new Library();
    Configuration swConfig;
    ContextMenu addSWNodeMenu;
    Group swUIObjects = new Group();

    @FXML
    Tab hwTab;

    @FXML
    TreeView<String> hwTreeView;

    @FXML
    AnchorPane hwPane;

    ROSLabTree hwTree;
    Library hwLibrary = new Library();
    Configuration hwConfig;
    ContextMenu addHWNodeMenu;
    Group hwUIObjects = new Group();

    @FXML
    Tab eeTab;

    @FXML
    TreeView<String> eeTreeView;

    @FXML
    AnchorPane eePane;

    ROSLabTree eeTree;
    Library eeLibrary = new Library();
    Configuration eeConfig;
    ContextMenu addEENodeMenu;
    Group eeUIObjects = new Group();

    @FXML
    ScrollPane swScroll;

    Random r = new Random();
    Rectangle selectionRectangle;
    double selectionX;
    double selectionY;
    Line addPortLine;
    Point portLineStart = new Point();
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert swTreeView != null : "fx:id\"swTreeView\" was not injected";
        assert hwTreeView != null : "fx:id\"hwTreeView\" was not injected";
        assert eeTreeView != null : "fx:id\"eeTreeView\" was not injected";

        BasicConfigurator.configure();

        // TODO: get highlighting and selection of Nodes based on selection
        // rectangle
        // enableSelectionRectangle(swPane);
        // enableSelectionRectangle(hwPane);
        // enableSelectionRectangle(eePane);

        // PythonLibraryHelper p = new PythonLibraryHelper();

        swConfig = new Configuration("Demo", swLibrary);
        swTree = new ROSLabTree(swLibrary, swConfig, this);
        swPane.getChildren().add(swUIObjects);
        swTreeView.setRoot(swTree);
        swTreeView.setShowRoot(false);
        swTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return swTree.new TreeCellImpl();
            }
        });

        eeLibrary = Library.loadBaseElectronicsLibrary();
        eeConfig = new Configuration("Demo", eeLibrary);
        eeTree = new ROSLabTree(eeLibrary, eeConfig, this);
        eePane.getChildren().add(eeUIObjects);
        eeTreeView.setRoot(eeTree);
        eeTreeView.setShowRoot(false);
        eeTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return eeTree.new TreeCellImpl();
            }
        });

        hwConfig = new Configuration("Demo", hwLibrary);
        hwTree = new ROSLabTree(hwLibrary, hwConfig, this);
        hwPane.getChildren().add(hwUIObjects);
        hwTreeView.setRoot(hwTree);
        hwTreeView.setShowRoot(false);
        hwTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return hwTree.new TreeCellImpl();
            }
        });

        createCanvasContextMenu(swLibrary, swConfig);
        createCanvasContextMenu(eeLibrary, eeConfig);
        createCanvasContextMenu(hwLibrary, hwConfig);
        // addDragDrop(swPane);
    }

    public void createCanvasContextMenu(final Library lib, final Configuration config) {
        final AnchorPane pane;
        final Group uiObjects;
        final ContextMenu menu;
        if (swLibrary.equals(lib)) {
            pane = swPane;
            uiObjects = swUIObjects;
            addSWNodeMenu = new ContextMenu();
            menu = addSWNodeMenu;
        }
        else if (hwLibrary.equals(lib)) {
            pane = hwPane;
            uiObjects = hwUIObjects;
            addHWNodeMenu = new ContextMenu();
            menu = addHWNodeMenu;
        }
        else if (eeLibrary.equals(lib)) {
            pane = eePane;
            uiObjects = eeUIObjects;
            addEENodeMenu = new ContextMenu();
            menu = addEENodeMenu;
        }
        else {
            return;
        }

        MenuItem addNodeItem = new MenuItem("Add Node");
        addNodeItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showNewNodeDialog(lib);
            }
        });
        menu.getItems().add(addNodeItem);

        if (!lib.equals(swLibrary)) {
            MenuItem addLinkItem = new MenuItem("Add Link");
            addLinkItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showNewLinkDialog(config);
                }
            });
            menu.getItems().add(addLinkItem);
        }

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY && !uiObjects.getChildren().contains(mouseEvent.getTarget())) { // TODO
                    // test
                    menu.show(swPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
                else if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    menu.hide();
                }
            }
        });
    }

    // private void enableSelectionRectangle(final Pane p) {
    // p.setOnMousePressed(new EventHandler<MouseEvent>() {
    // @Override
    // public void handle(MouseEvent mouseEvent) {
    // selectionX = mouseEvent.getX();
    // selectionY = mouseEvent.getY();
    // selectionRectangle = new Rectangle(selectionX, selectionY, 0, 0);
    // selectionRectangle.getStyleClass().add("SelectionRectangle");
    // p.getChildren().add(selectionRectangle);
    // }
    // });
    // p.setOnMouseReleased(new EventHandler<MouseEvent>() {
    // @Override
    // public void handle(MouseEvent mouseEvent) {
    // p.getChildren().remove(selectionRectangle);
    // }
    // });
    //
    // p.setOnMouseDragged(new EventHandler<MouseEvent>() {
    // @Override
    // public void handle(MouseEvent mouseEvent) {
    // double x = mouseEvent.getX();
    // double y = mouseEvent.getY();
    // if (x < selectionX) {
    // selectionRectangle.setX(x);
    // selectionRectangle.setWidth(selectionX - x);
    // }
    // else {
    // selectionRectangle.setWidth(x - selectionRectangle.getX());
    // }
    // if (y < selectionY) {
    // selectionRectangle.setY(y);
    // selectionRectangle.setHeight(selectionY - y);
    // }
    // else {
    // selectionRectangle.setHeight(y - selectionRectangle.getY());
    // }
    // }
    // });
    // }

    public void addLibraryNode(ROSNode n) {
        swLibrary.addNode(n);
        swTree.addLibraryNode(n);
    }

    public void removeLibraryNode(ROSNode n) {
        swLibrary.removeNode(n);
        swTree.removeLibraryNode(n);
    }

    public void updateLibraryNode(ROSNode n) {
        removeLibraryNode(n);
        addLibraryNode(n);
    }

    public void addLibraryNode(Circuit n) {
        eeLibrary.addNode(n);
        eeTree.addLibraryNode(n);
    }

    public void removeLibraryNode(Circuit n) {
        eeLibrary.removeNode(n);
        eeTree.removeLibraryNode(n);
    }

    public void updateLibraryNode(Circuit n) {
        removeLibraryNode(n);
        addLibraryNode(n);
    }

    public void addLibraryNode(HWBlock n) {
        hwLibrary.addNode(n);
        hwTree.addLibraryNode(n);
    }

    public void removeLibraryNode(HWBlock n) {
        hwLibrary.removeNode(n);
        hwTree.removeLibraryNode(n);
    }

    public void updateLibraryNode(HWBlock n) {
        removeLibraryNode(n);
        addLibraryNode(n);
    }

    public void addConfigNode(Node n) {
        UINode uin = null;
        if (n.getUINode() == null) {
            uin = new UINode(n, r.nextInt(400), r.nextInt(400));
        }
        else {
            uin = n.getUINode();
        }
        Group grp = null;
        switch (uin.getNode().getClass().getSimpleName()) {
            case "ROSNode":
                swConfig.addNode(n);
                swTree.addConfigNode(n);
                refreshSWConfigLinks();
                grp = swUIObjects;
                uin.setScrollPane(swScroll);
                break;
            case "HWBlock":
                hwConfig.addNode(n);
                hwTree.addConfigNode(n);
                grp = hwUIObjects;
                break;
            case "Circuit":
                eeConfig.addNode(n);
                eeTree.addConfigNode(n);
                grp = eeUIObjects;
                break;
        }
        uin.addToGroup(grp, this);

        // Order all of the UI objects
        for (Object nn : grp.getChildren().toArray()) {
            if (nn instanceof UINode) {
                ((UINode) nn).toTheFront();
            }
            if (nn instanceof UILink) {
                ((UILink) nn).toBack();
            }
        }
    }

    public void addConfigLink(Link l) {
        UILink uil = new UILink(l);
        l.setUILink(uil);
        Group grp = null;
        switch (l.getSrc().getParent().getClass().getSimpleName()) {
            case "ROSNode":
                swConfig.addLink(l);
                swTree.addConfigLink(l);
                grp = swUIObjects;
                break;
            case "HWBlock":
                hwConfig.addLink(l);
                hwTree.addConfigLink(l);
                grp = hwUIObjects;
                break;
            case "Circuit":
                eeConfig.addLink(l);
                eeTree.addConfigLink(l);
                grp = eeUIObjects;
                break;
        }

        grp.getChildren().add(l.getUILink());
        // Order all of the UI objects
        for (Object nn : grp.getChildren().toArray()) {
            if (nn instanceof UINode) {
                ((UINode) nn).toTheFront();
            }
            if (nn instanceof UILink) {
                ((UILink) nn).toBack();
            }
        }
    }

    public void removeConfigNode(Node n) {
        switch (n.getClass().getSimpleName()) {
            case "ROSNode":
                for (UIEndpoint e : n.getUINode().getUIEndpoints()) {
                    for (UILink l : e.getUILinks()) {
                        swConfig.removeLink(l.getLink());
                        swTree.removeConfigLink(l.getLink());
                        if (e.equals(l.getSrc())) {
                            l.getDest().removeUILink(l);
                            if ("controller".equals(l.getDest().getParentNode().getAnnotation("custom-type")) && l.getDest().getUILinks().size() == 0) {
                                removeConfigPort(l.getDest().getParentNode(), l.getDest().getName());
                            }
                        }
                        else {
                            l.getSrc().removeUILink(l);
                            if ("controller".equals(l.getSrc().getParentNode().getAnnotation("custom-type")) && l.getSrc().getUILinks().size() == 0) {
                                removeConfigPort(l.getSrc().getParentNode(), l.getSrc().getName());
                            }
                        }
                        swUIObjects.getChildren().remove(l);
                    }
                    e.getUILinks().clear();
                    e.removeFromGroup(swUIObjects);
                }
                swConfig.removeNode(n);
                swTree.removeConfigNode(n);
                n.getUINode().removeFromGroup(swUIObjects);
                break;
            case "HWBlock":
                hwConfig.removeNode(n);
                hwTree.removeConfigNode(n);
                n.getUINode().removeFromGroup(hwUIObjects);
                break;
            case "Circuit":
                eeConfig.removeNode(n);
                eeTree.removeConfigNode(n);
                n.getUINode().removeFromGroup(eeUIObjects);
                break;
        }

        // Remove any links associated with this node
        for (Endpoint e : n.getEndpoints()) {
            Iterator<? extends Link> linkit = e.getLinks().iterator();
            while (linkit.hasNext()) {
                Link l = linkit.next();
                linkit.remove();
                removeConfigLink(l);
            }
        }
        n = null;
    }

    public void removeMatchingConfigNodes(Node node, Configuration config) {
        ArrayList<Node> toRemove = new ArrayList<Node>();
        boolean isCustomTopic = "topic".equals(node.getAnnotation("custom-type"));
        for (Node n : config.getNodes()) {
            if (node.getName().equals(n.getSpec().getName())) {
                toRemove.add(n);
            }
            if (isCustomTopic && "controller".equals(n.getSpec().getAnnotation("custom-type"))) {
                String topicName = ((ROSNode) node).getPorts().keySet().iterator().next();
                removeConfigPort(n, topicName);
            }
        }
        Iterator<Node> it = toRemove.iterator();
        while (it.hasNext()) {
            Node n = it.next();
            it.remove();
            removeConfigNode(n);
        }
    }

    public void removeConfigLink(Link l) {
        switch (l.getSrc().getClass().getSimpleName()) {
            case "ROSPort":
                swConfig.removeLink(l);
                swTree.removeConfigLink(l);
                l.getUILink().removeFromGroup(swUIObjects);
                break;
            case "Joint":
                hwConfig.removeLink(l);
                hwTree.removeConfigLink(l);
                l.getUILink().removeFromGroup(hwUIObjects);
                break;
            case "Circuit":
                eeConfig.removeLink(l);
                eeTree.removeConfigLink(l);
                l.getUILink().removeFromGroup(eeUIObjects);
                break;
        }
        l.destroy();
        l = null;
    }

    public void loadSWLibrary(Path swLib) {
        swLibrary.loadLibrary(swLib);
        swConfig.setLibrary(swLibrary);
        swTree = new ROSLabTree(swLibrary, swConfig, this);
        swUIObjects.getChildren().clear();
        swTreeView.setRoot(swTree);
        swTreeView.setShowRoot(false);
        swTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return swTree.new TreeCellImpl();
            }
        });
    }

    /**
     * @return the library
     */
    public Library getSWLibrary() {
        return swLibrary;
    }

    public void loadSWConfig(Path swCon, Library swLib) {
        swLibrary = swLib;
        swConfig = ConfigurationParser.parseConfigurationYAML(swCon, swLibrary);
        swTree = new ROSLabTree(swLibrary, swConfig, this);
        swUIObjects.getChildren().clear();
        swTreeView.setRoot(swTree);
        swTreeView.setShowRoot(false);
        swTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return swTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    public void loadSWConfig(String swCon, Library swLib) {
        swLibrary = swLib;
        swConfig = ConfigurationParser.parseConfigurationYAML(swCon, swLibrary);
        swTree = new ROSLabTree(swLibrary, swConfig, this);
        swUIObjects.getChildren().clear();
        swTreeView.setRoot(swTree);
        swTreeView.setShowRoot(false);
        swTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return swTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    /**
     * @return the config
     */
    public Configuration getSWConfig() {
        return swConfig;
    }

    public void loadHWLibrary(Path hwLib) {
        hwLibrary.loadLibrary(hwLib);
        hwConfig.setLibrary(hwLibrary);
        hwTree = new ROSLabTree(hwLibrary, hwConfig, this);
        hwUIObjects.getChildren().clear();
        hwTreeView.setRoot(hwTree);
        hwTreeView.setShowRoot(false);
        hwTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return hwTree.new TreeCellImpl();
            }
        });
    }

    /**
     * @return the library
     */
    public Library getHWLibrary() {
        return hwLibrary;
    }

    public void loadHWConfig(Path hwCon, Library hwLib) {
        hwLibrary = hwLib;
        hwConfig = ConfigurationParser.parseConfigurationYAML(hwCon, hwLibrary);
        hwTree = new ROSLabTree(hwLibrary, hwConfig, this);
        hwUIObjects.getChildren().clear();
        hwTreeView.setRoot(hwTree);
        hwTreeView.setShowRoot(false);
        hwTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return hwTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    public void loadHWConfig(String hwCon, Library hwLib) {
        hwLibrary = hwLib;
        hwConfig = ConfigurationParser.parseConfigurationYAML(hwCon, hwLibrary);
        hwTree = new ROSLabTree(hwLibrary, hwConfig, this);
        hwUIObjects.getChildren().clear();
        hwTreeView.setRoot(hwTree);
        hwTreeView.setShowRoot(false);
        hwTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return hwTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    /**
     * @return the config
     */
    public Configuration getHWConfig() {
        return hwConfig;
    }

    public void loadEELibrary(Path eeLib) {
        eeLibrary.loadLibrary(eeLib);
        eeConfig.setLibrary(eeLibrary);
        eeTree = new ROSLabTree(eeLibrary, eeConfig, this);
        eeUIObjects.getChildren().clear();
        eeTreeView.setRoot(eeTree);
        eeTreeView.setShowRoot(false);
        eeTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return eeTree.new TreeCellImpl();
            }
        });
    }

    /**
     * @return the library
     */
    public Library getEELibrary() {
        return eeLibrary;
    }

    public void loadEEConfig(Path eeCon, Library eeLib) {
        eeLibrary = eeLib;
        eeConfig = ConfigurationParser.parseConfigurationYAML(eeCon, eeLibrary);
        eeTree = new ROSLabTree(eeLibrary, eeConfig, this);
        eeUIObjects.getChildren().clear();
        eeTreeView.setRoot(eeTree);
        eeTreeView.setShowRoot(false);
        eeTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return eeTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    public void loadEEConfig(String eeCon, Library eeLib) {
        eeLibrary = eeLib;
        eeConfig = ConfigurationParser.parseConfigurationYAML(eeCon, eeLibrary);
        eeTree = new ROSLabTree(eeLibrary, eeConfig, this);
        eeUIObjects.getChildren().clear();
        eeTreeView.setRoot(eeTree);
        eeTreeView.setShowRoot(false);
        eeTreeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return eeTree.new TreeCellImpl();
            }
        });
        refreshUINodes();
    }

    /**
     * @return the config
     */
    public Configuration getEEConfig() {
        return eeConfig;
    }

    @FXML
    private void openLibrary() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YAML files (*.yaml)", "*.yaml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File openFile = fileChooser.showOpenDialog(primaryStage);

        if (openFile != null) {
            switch (mainTabPane.getSelectionModel().getSelectedItem().getText()) {
                case "Software":
                    loadSWLibrary(openFile.toPath());
                    break;
                case "Electrical":
                    loadEELibrary(openFile.toPath());
                    break;
                case "Mechanical":
                    loadHWLibrary(openFile.toPath());
                    break;
            }
        }
    }

    @FXML
    private void saveLibrary() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YAML files (*.yaml)", "*.yaml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Set initial filename
        switch (mainTabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Software":
                fileChooser.setInitialFileName("swLibrary.yaml");
                break;
            case "Electrical":
                fileChooser.setInitialFileName("eeLibrary.yaml");
                break;
            case "Mechanical":
                fileChooser.setInitialFileName("hwLibrary.yaml");
                break;
        }

        // Show save file dialog
        File saveFile = fileChooser.showSaveDialog(primaryStage);

        if (saveFile != null) {
            switch (mainTabPane.getSelectionModel().getSelectedItem().getText()) {
                case "Software":
                    LibraryParser.emitLibraryYAML(swLibrary, saveFile);
                    break;
                case "Electrical":
                    LibraryParser.emitLibraryYAML(eeLibrary, saveFile);
                    break;
                case "Mechanical":
                    LibraryParser.emitLibraryYAML(hwLibrary, saveFile);
                    break;
            }
        }
    }

    @FXML
    private void openConfiguration() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LAB files (*.lab)", "*.lab");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File openFile = fileChooser.showOpenDialog(primaryStage);

        // Unzip package and load all YAML files
        FileInputStream fis = null;
        ZipInputStream zipIs = null;
        ZipEntry zEntry = null;

        if (openFile != null) {
            // Read the Library YAML files
            try {
                fis = new FileInputStream(openFile);
                zipIs = new ZipInputStream(new BufferedInputStream(fis));
                while ((zEntry = zipIs.getNextEntry()) != null) {
                    try {
                        byte[] tmp = new byte[4 * 1024];
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        int size = 0;
                        while ((size = zipIs.read(tmp)) != -1) {
                            bos.write(tmp, 0, size);
                        }

                        bos.flush();

                        switch (zEntry.getName()) {
                            case "swLibrary.yaml":
                                swLibrary = LibraryParser.parseLibraryYAML(bos.toString());
                                break;
                            case "eeLibrary.yaml":
                                eeLibrary = LibraryParser.parseLibraryYAML(bos.toString());
                                break;
                            case "hwLibrary.yaml":
                                hwLibrary = LibraryParser.parseLibraryYAML(bos.toString());
                                break;
                        }

                        bos.reset();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (zipIs != null) {
                        zipIs.close();
                    }
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            // Read the Configuration YAML files
            try {
                fis = new FileInputStream(openFile);
                zipIs = new ZipInputStream(new BufferedInputStream(fis));
                while ((zEntry = zipIs.getNextEntry()) != null) {
                    try {
                        byte[] tmp = new byte[4 * 1024];
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        int size = 0;
                        while ((size = zipIs.read(tmp)) != -1) {
                            bos.write(tmp, 0, size);
                        }

                        bos.flush();

                        logger.debug(bos.toString());

                        switch (zEntry.getName()) {
                            case "swConfig.yaml":
                                loadSWConfig(bos.toString(), swLibrary);
                                break;
                            case "eeConfig.yaml":
                                loadEEConfig(bos.toString(), eeLibrary);
                                break;
                            case "hwConfig.yaml":
                                loadHWConfig(bos.toString(), hwLibrary);
                                break;
                        }

                        bos.reset();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (zipIs != null) {
                        zipIs.close();
                    }
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void saveConfiguration() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("LAB files (*.lab)", "*.lab");
        fileChooser.getExtensionFilters().add(extFilter);

        // Set initial filename
        fileChooser.setInitialFileName("MyConfig_" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".lab");

        // Show save file dialog
        File saveFile = fileChooser.showSaveDialog(primaryStage);

        if (saveFile != null) {
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            try {
                fos = new FileOutputStream(saveFile);
                zipOut = new ZipOutputStream(new BufferedOutputStream(fos));

                InputStream is = new ByteArrayInputStream(ConfigurationParser.emitConfigurationYAML(swConfig).getBytes());
                ZipEntry ze = new ZipEntry("swConfig.yaml");
                zipOut.putNextEntry(ze);
                byte[] tmp = new byte[4 * 1024];
                int size = 0;
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                is = new ByteArrayInputStream(LibraryParser.emitLibraryYAML(swLibrary).getBytes());
                ze = new ZipEntry("swLibrary.yaml");
                zipOut.putNextEntry(ze);
                tmp = new byte[4 * 1024];
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                is = new ByteArrayInputStream(ConfigurationParser.emitConfigurationYAML(eeConfig).getBytes());
                ze = new ZipEntry("eeConfig.yaml");
                zipOut.putNextEntry(ze);
                tmp = new byte[4 * 1024];
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                is = new ByteArrayInputStream(LibraryParser.emitLibraryYAML(eeLibrary).getBytes());
                ze = new ZipEntry("eeLibrary.yaml");
                zipOut.putNextEntry(ze);
                tmp = new byte[4 * 1024];
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                is = new ByteArrayInputStream(ConfigurationParser.emitConfigurationYAML(hwConfig).getBytes());
                ze = new ZipEntry("hwConfig.yaml");
                zipOut.putNextEntry(ze);
                tmp = new byte[4 * 1024];
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                is = new ByteArrayInputStream(LibraryParser.emitLibraryYAML(hwLibrary).getBytes());
                ze = new ZipEntry("hwLibrary.yaml");
                zipOut.putNextEntry(ze);
                tmp = new byte[4 * 1024];
                while ((size = is.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                zipOut.closeEntry();

                zipOut.close();
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void tabChanged() {
        // TODO Nothing yet...
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person
     *            the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showNewLinkDialog(Configuration config) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/general/NewLinkDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Link");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewLinkDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEndpoints(config.getEndpoints());
            controller.setRLController(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and
     * true
     * is returned.
     *
     * @param person
     *            the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showNewNodeDialog(Library library) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/general/NewNodeDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Node");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewNodeDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            ArrayList<Node> nodeTypes = new ArrayList<Node>(library.getNodes());
            ArrayList<String> configNodeNames = new ArrayList<String>();
            Configuration config = null;
            if (library == swLibrary) {
                for (Node configNode : swConfig.getNodes()) {
                    configNodeNames.add(configNode.getName());
                    if ("controller".equals(configNode.getAnnotation("custom-type"))) {
                        Node toRemove = null;
                        for (Node n : nodeTypes) {
                            if (configNode.getSpec().getName().equals(n.getName())) {
                                toRemove = n;
                            }
                        }
                        nodeTypes.remove(toRemove);
                    }
                }
                config = swConfig;
            }
            else if (library == hwLibrary) {
                config = hwConfig;
            }
            else if (library == eeLibrary) {
                config = eeConfig;
            }
            for (Node configNode : config.getNodes()) {
                configNodeNames.add(configNode.getName());
            }
            controller.setConfigNodeNames(configNodeNames);
            controller.setNodes(nodeTypes);
            controller.setRLController(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isAddClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and
     * true
     * is returned.
     *
     * @param person
     *            the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCustomNodeDialog(String nodeType) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/software/NewCustom" + nodeType + "Dialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Custom " + nodeType + " Node");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            if ("Controller".equals(nodeType)) {
                NewCustomControllerDialog controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setRLController(this);
                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return controller.isAddClicked();
            }
            else {
                NewCustomTopicDialog controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setRLController(this);
                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return controller.isAddClicked();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEditRateDialog(Node node) {
        if (!(node instanceof ROSNode && "controller".equals(node.getAnnotation("custom-type")))) {
            return false;
        }
        ROSNode rosNode = (ROSNode) node;
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/software/EditRateDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Rate");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditRateDialog controller = loader.getController();
            controller.setNode(rosNode);
            controller.setDialogStage(dialogStage);
            controller.setRLController(this);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isAddClicked();

        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showLoadLibraryDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/software/LoadLibraryDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Load Library");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            LoadLibraryDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRLController(this);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isAddClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and
     * true
     * is returned.
     *
     * @param node
     * @param person
     *            the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showNewCustomPortDialog(Node node) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ui/software/NewCustomPortDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Port");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            NewCustomPortDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNode(node);
            controller.setRLController(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isAddClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addConfigPort(Node node, String pName, String pType, boolean isSub) {
        ROSPort toAdd = new ROSPort(pName, ((ROSNode) node), new ROSTopic(pName, new ROSMsgType(pType), isSub), false, false);
        ((ROSNode) node).addPort(toAdd); // Add to this node's features
        ((ROSNode) node.getSpec()).addPort(toAdd); // Add to library node's features
        updateLibraryNode((ROSNode) node.getSpec()); // Update library with library node
        // for (Node n : swConfig.getNodes()) {
        // if (n instanceof ROSNode && n.getSpec().equals(node.getSpec())) {
        // ((ROSNode) n).addPort(new ROSPort(pName, ((ROSNode) node), new
        // ROSTopic(pName, new ROSMsgType(pType), isSub), false, false));
        // }
        // }
        refreshConfigPorts();
        refreshSWConfigLinks();
    }

    public void removeConfigPort(Node node, String pName) {
        ((ROSNode) node).removePort(pName);
        // Add to library node's features
        ((ROSNode) node.getSpec()).removePort(pName);
        // Update library with library node
        updateLibraryNode((ROSNode) node.getSpec());
        // for (Node n : swConfig.getNodes()) {
        // if (n instanceof ROSNode && n.getSpec().equals(node.getSpec())) {
        // ((ROSNode) n).removePort(pName);
        // }
        // }
        refreshConfigPorts();
        refreshSWConfigLinks();
    }

    public void refreshConfigPorts() {
        for (Node n : swConfig.getNodes()) {
            UINode uin = n.getUINode();
            for (UIEndpoint e : uin.getUIEndpoints()) {
                e.removeFromGroup(swUIObjects);
            }
            uin.resetEndpoints(this);
            for (UIEndpoint e : uin.getUIEndpoints()) {
                e.addToGroup(swUIObjects);
            }
        }
    }

    // TODO Switch system.out calls to Debug calls
    public void refreshSWConfigLinks() {
        for (Link link : swConfig.getLinks()) {
            removeConfigLink(link);
        }
        swConfig.getLinks().clear();
        for (Node nodeA : swConfig.getNodes()) {
            for (Node nodeB : swConfig.getNodes()) {
                for (Endpoint endA : nodeA.getEndpoints()) {
                    for (Endpoint endB : nodeB.getEndpoints()) {
                        System.out.println("Matching " + endB.getParent().getName() + " " + endA.getParent().getName());
                        if (endA.equals(endB)) {
                            continue;
                        }
                        if (endA.canConnect(endB) && endA instanceof ROSPort && ((ROSPort) endA).isSubscriber()) {
                            addConfigLink(endB.connect(endA));
                            System.out.println("Adding");
                        }
                    }
                }
            }
        }
        System.out.println("Config link count: " + swConfig.getLinks().size());
        for (Link l : swConfig.getLinks()) {
            System.out.println(l.getSrc().getParent().getName() + " -> " + l.getDest().getParent().getName());
        }
    }

    public void refreshUINodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.addAll(swConfig.getNodes());
        nodes.addAll(eeConfig.getNodes());
        nodes.addAll(hwConfig.getNodes());
        for (Node n : nodes) {
            if (n.getUINode() == null) {
                n.setUINode(new UINode(n, 400, 400));
            }
            Group grp = null;
            switch (n.getClass().getSimpleName()) {
                case "ROSNode":
                    grp = swUIObjects;
                    break;
                case "HWBlock":
                    grp = hwUIObjects;
                    break;
                case "Circuit":
                    grp = eeUIObjects;
                    break;
            }
            if (!grp.getChildren().contains(n.getUINode())) {
                n.getUINode().addToGroup(grp, this);
            }

            // Order all of the UI objects
            for (Object nn : grp.getChildren().toArray()) {
                if (nn instanceof UINode) {
                    ((UINode) nn).toTheFront();
                }
                if (nn instanceof UILink) {
                    ((UILink) nn).toBack();
                }
            }
        }
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getStage() {
        return this.primaryStage;
    }

    // TODO Update method for use with all types of configs, not just SW
    public void killDrawTasks() {
        for (Node n : swConfig.getNodes()) {
            for (Endpoint e : n.getEndpoints()) {
                e.getUIEndpoint().killDrawTask();
            }
        }
    }
}
