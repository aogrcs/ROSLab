package roslab.ui.general;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;

import org.controlsfx.dialog.Dialogs;

import roslab.ROSLabController;
import roslab.model.electronics.Circuit;
import roslab.model.electronics.Pin;
import roslab.model.general.Configuration;
import roslab.model.general.Feature;
import roslab.model.general.Library;
import roslab.model.general.Link;
import roslab.model.general.Node;
import roslab.model.software.ROSNode;
import roslab.processors.electronics.EagleSchematic;
import roslab.processors.software.ROSNodeCodeGenerator;

public class ROSLabTree extends TreeItem<String> {

    LibraryTreeItem libraryTree;
    ConfigTreeItem configTree;

    @SuppressWarnings("unchecked")
    public ROSLabTree(Library lib, Configuration conf, ROSLabController controller) {
        libraryTree = new LibraryTreeItem(controller, lib);
        configTree = new ConfigTreeItem(controller, conf, lib);

        // Add to top-level tree
        getChildren().addAll(libraryTree, configTree);
    }

    public class ContextMenuTreeItem extends TreeItem<String> {
        public ContextMenu menu = new ContextMenu();
        private ROSLabController controller;

        public ContextMenuTreeItem(String title) {
            super(title);
        }

        public ContextMenuTreeItem(String title, ROSLabController controller) {
            super(title);
            this.controller = controller;
        }

        public ContextMenu getMenu() {
            if ("ROSNode".equals(getValue()) && controller != null) {
                MenuItem controllerItem = new MenuItem("Add Custom Controller Node...");
                controllerItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.showCustomNodeDialog("Controller");
                    }
                });
                MenuItem topicItem = new MenuItem("Add Custom Topic Node...");
                topicItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        controller.showCustomNodeDialog("Topic");
                    }
                });
                return new ContextMenu(controllerItem, topicItem);
            }
            else {
                return new ContextMenu();
            }
        }

        public void setMenu(ContextMenu menu) {
            this.menu = menu;
        }
    }

    public class LibraryTreeItem extends ContextMenuTreeItem {
        private ROSLabController controller;
        private Library library;

        public LibraryTreeItem(ROSLabController cont, Library lib) {
            super("Library (" + lib.getName() + ")");
            controller = cont;
            library = lib;

            // Add library nodes
            for (Node n : library.getNodes()) {
                addNode(n);
            }
            setExpanded(true);
        }

        public LibraryTreeItem() {
            super("Library");
        }

        public void addNode(Node n) {
            library.addNode(n);
            boolean nodeAdded = false;
            for (TreeItem<String> s : this.getChildren()) {
                if (n.getClass().getSimpleName().equals(s.getValue())) {
                    NodeTreeItem newNode = new NodeTreeItem(n, this.controller);
                    for (Feature f : n.getFeatures().values()) {
                        newNode.getChildren().add(new ContextMenuTreeItem(f.toString()));
                    }
                    s.getChildren().add(newNode);
                    nodeAdded = true;
                }
            }
            if (!nodeAdded) {
                ContextMenuTreeItem newItem = new ContextMenuTreeItem(n.getClass().getSimpleName(), controller);
                NodeTreeItem newNode = new NodeTreeItem(n, this.controller);
                for (Feature f : n.getFeatures().values()) {
                    newNode.getChildren().add(new ContextMenuTreeItem(f.toString()));
                }
                newItem.getChildren().add(newNode);
                this.getChildren().add(newItem);
                nodeAdded = true;
            }
        }

        public void removeNode(Node n) {
            library.removeNode(n);
            int removeIndex2 = -1;
            for (TreeItem<String> s : this.getChildren()) {
                if (n.getClass().getSimpleName().equals(s.getValue())) {
                    int removeIndex = -1;
                    for (TreeItem<String> i : s.getChildren()) {
                        if (((NodeTreeItem) i).node == n) {
                            removeIndex = s.getChildren().indexOf(i);
                        }
                    }
                    if (removeIndex != -1) {
                        s.getChildren().remove(removeIndex);
                    }
                    if (s.getChildren().isEmpty()) {
                        removeIndex2 = this.getChildren().indexOf(s);
                    }
                }
            }
            if (removeIndex2 != -1) {
                this.getChildren().remove(removeIndex2);
            }
        }

        @Override
        public ContextMenu getMenu() {
            MenuItem libraryItem = new MenuItem("Load Library");
            libraryItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.showLoadLibraryDialog();
                }
            });
            return new ContextMenu(libraryItem);
        }
    }

    public class ConfigTreeItem extends ContextMenuTreeItem {
        private ROSLabController controller;
        private Configuration configuration;
        private Library library;
        private ContextMenuTreeItem configNodesTree = new ContextMenuTreeItem("Nodes");
        private ContextMenuTreeItem configLinksTree = new ContextMenuTreeItem("Links");

        public ConfigTreeItem(ROSLabController cont, Configuration config, Library lib) {
            super("Configuration (" + config.getName() + ")");
            controller = cont;
            configuration = config;
            library = lib;

            // Add Configuration nodes
            for (Node n : configuration.getNodes()) {
                addNode(n);
            }
            this.getChildren().add(configNodesTree);

            // Add Configuration links
            for (Link l : configuration.getLinks()) {
                addLink(l);
            }
            this.getChildren().add(configLinksTree);

            MenuItem nItem = new MenuItem("Add node...");
            nItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.showNewNodeDialog(library);
                }
            });
            configNodesTree.setMenu(new ContextMenu(nItem));

            MenuItem mItem = new MenuItem("Add link...");
            mItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.showNewLinkDialog(configuration);
                }
            });
            configLinksTree.setMenu(new ContextMenu(mItem));
            setExpanded(true);
        }

        public void addNode(Node n) {
            configuration.addNode(n);
            boolean nodeAdded = false;
            for (TreeItem<String> s : configNodesTree.getChildren()) {
                if (n.getClass().getSimpleName().equals(s.getValue())) {
                    s.getChildren().add(new NodeTreeItem(n, controller));
                    nodeAdded = true;
                }
            }
            if (!nodeAdded) {
                ContextMenuTreeItem newItem = new ContextMenuTreeItem(n.getClass().getSimpleName());
                newItem.getChildren().add(new NodeTreeItem(n, controller));
                configNodesTree.getChildren().add(newItem);
                nodeAdded = true;
            }
        }

        public void addLink(Link l) {
            configuration.addLink(l);
            boolean nodeAdded = false;
            for (TreeItem<String> s : configLinksTree.getChildren()) {
                if (l.getClass().getSimpleName().equals(s.getValue())) {
                    s.getChildren().add(new LinkTreeItem(l, controller));
                    nodeAdded = true;
                }
            }
            if (!nodeAdded) {
                ContextMenuTreeItem newItem = new ContextMenuTreeItem(l.getClass().getSimpleName());
                newItem.getChildren().add(new LinkTreeItem(l, controller));
                configLinksTree.getChildren().add(newItem);
                nodeAdded = true;
            }
        }

        public void removeNode(Node n) {
            configuration.removeNode(n);
            int removeIndex2 = -1;
            for (TreeItem<String> s : configNodesTree.getChildren()) {
                if (n.getClass().getSimpleName().equals(s.getValue())) {
                    int removeIndex = -1;
                    for (TreeItem<String> i : s.getChildren()) {
                        if (((NodeTreeItem) i).node == n) {
                            removeIndex = s.getChildren().indexOf(i);
                        }
                    }
                    if (removeIndex != -1) {
                        s.getChildren().remove(removeIndex);
                    }
                    if (s.getChildren().isEmpty()) {
                        removeIndex2 = configNodesTree.getChildren().indexOf(s);
                    }
                }
            }
            if (removeIndex2 != -1) {
                configNodesTree.getChildren().remove(removeIndex2);
            }
        }

        public void removeLink(Link l) {
            configuration.removeLink(l);
            int removeIndex2 = -1;
            for (TreeItem<String> s : configLinksTree.getChildren()) {
                if (l.getClass().getSimpleName().equals(s.getValue())) {
                    int removeIndex = -1;
                    for (TreeItem<String> i : s.getChildren()) {
                        if (((LinkTreeItem) i).link == l) {
                            removeIndex = s.getChildren().indexOf(i);
                        }
                    }
                    if (removeIndex != -1) {
                        s.getChildren().remove(removeIndex);
                    }
                    if (s.getChildren().isEmpty()) {
                        removeIndex2 = configLinksTree.getChildren().indexOf(s);
                    }
                }
            }
            if (removeIndex2 != -1) {
                configLinksTree.getChildren().remove(removeIndex2);
            }
        }

        @Override
        public ContextMenu getMenu() {
            if (configuration == controller.getSWConfig()) {
                MenuItem sourceItem = new MenuItem("Generate source code only");
                sourceItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Handle ROSNode nodes
                        for (Node n : configuration.getNodesOfType(ROSNode.class)) {
                            try {
                                if (n.getAnnotation("custom-type") != null && n.getAnnotation("custom-type").equals("controller")) {
                                    ROSNodeCodeGenerator.buildNode((ROSNode) n, new File(n.getName() + ".cpp"));
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                // MenuItem packageItem = new MenuItem("Generate ROS package");
                // packageItem.setOnAction(new EventHandler<ActionEvent>() {
                // @Override
                // public void handle(ActionEvent event) {
                // Alert alert = new Alert(AlertType.INFORMATION);
                // alert.setTitle("ROSLab");
                // alert.setHeaderText("Select Workspace");
                // alert.setContentText("Please select your initialized ROS workspace directory.");
                // alert.showAndWait();
                // DirectoryChooser directoryChooser = new DirectoryChooser();
                // directoryChooser.setTitle("Select ROS Workspace directory");
                // File file = directoryChooser.showDialog(controller.getStage());
                // if(file != null) {
                // System.out.println(file.getPath());
                // }
                // //prompt for workspace location
                // //catkin_create_pkg [package_name] [dependency messages]* roscpp rospy std_msgs
                // for (Node n : configuration.getNodesOfType(ROSNode.class)) {
                // try {
                // if (n.getAnnotation("custom-type") != null && n.getAnnotation("custom-type").equals("controller")) {
                // ROSNodeCodeGenerator.buildNode((ROSNode) n, new File(n.getName() + ".cpp"));
                // }
                // }
                // catch (IOException e) {
                // e.printStackTrace();
                // }
                // }
                // //put cpp in package's src folder
                // //modify CMakeLists.txt (in the package, not the workspace)->
                // }
                // });
                // return new ContextMenu(sourceItem, packageItem);
                return new ContextMenu(sourceItem);
            }
            else if (configuration == controller.getEEConfig()) {
                MenuItem circuitItem = new MenuItem("Generate merged circuit");
                circuitItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Handle Circuit nodes
                        List<EagleSchematic> schematics = new ArrayList<EagleSchematic>();
                        List<Circuit> circuitsWithUnconnectedRequireds = new ArrayList<Circuit>();
                        for (Node n : configuration.getNodesOfType(Circuit.class)) {
                            Circuit c = (Circuit) n;
                            if (c.getUnconnectedRequiredPins().size() > 0) {
                                circuitsWithUnconnectedRequireds.add(c);
                            }
                            schematics.add(c.getSchematic());

                        }
                        if (circuitsWithUnconnectedRequireds.size() > 0) {
                            String circuitString = "";
                            for (Circuit c : circuitsWithUnconnectedRequireds) {
                                circuitString += c.getName() + "\n";
                                for (Pin p : c.getUnconnectedRequiredPins().values()) {
                                    circuitString += "  " + p.getName() + "\n";
                                }
                            }
                            Dialogs.create().owner(controller.getStage()).title("Missing Required Links")
                            .masthead("The following Circuit nodes are missing required links").message(circuitString).showError();
                        }
                        else if (schematics.size() > 1) {
                            EagleSchematic.connectWires(configuration.getLinks());
                            EagleSchematic.merge(schematics, "Merged.sch");
                        }
                    }
                });
                menu = new ContextMenu(circuitItem);
            }
            return menu;
        }
    }

    public class NodeTreeItem extends ContextMenuTreeItem {
        private Node node;
        private ROSLabController controller;

        public NodeTreeItem(Node node, ROSLabController controller) {
            super(node.getName());
            this.node = node;
            this.controller = controller;
        }

        @Override
        public ContextMenu getMenu() {
            // No menu if node is under Library tree and not a user-defined node
            if (this.getParent().getParent() instanceof LibraryTreeItem && node instanceof ROSNode) {
                if ("true".equals(node.getAnnotation("user-defined"))) {
                    MenuItem m2Item = new MenuItem("Delete");
                    m2Item.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            controller.removeMatchingConfigNodes(node, controller.getSWConfig());
                            controller.removeLibraryNode((ROSNode) node);
                        }
                    });
                    menu = new ContextMenu(m2Item);
                }
            }
            return menu;
        }
    }

    public class LinkTreeItem extends ContextMenuTreeItem {
        private Link link;
        private ROSLabController controller;

        public LinkTreeItem(Link link, ROSLabController controller) {
            super(link.getName());
            this.link = link;
            this.controller = controller;
        }

        @Override
        public ContextMenu getMenu() {
            MenuItem mItem = new MenuItem("Delete");
            mItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    controller.removeConfigLink(link);
                }
            });
            return new ContextMenu(mItem);
        }
    }

    public final class TreeCellImpl extends TreeCell<String> {

        // setOnDragDetected(new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        // /* drag was detected, start a drag-and-drop gesture */
        // /* allow any transfer mode */
        // Dragboard db = startDragAndDrop(TransferMode.ANY);
        //
        // /* Put a string on a dragboard */
        // ClipboardContent content = new ClipboardContent();
        // content.putString("node:" + item.node.getName());
        // db.setContent(content);
        //
        // event.consume();
        // }
        // });

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            }
            else {
                setText(getItem() == null ? "" : getItem().toString());
                setGraphic(getTreeItem().getGraphic());
                setContextMenu(((ContextMenuTreeItem) getTreeItem()).getMenu());
            }
        }
    }

    public void addLibraryNode(Node n) {
        libraryTree.addNode(n);
    }

    public void removeLibraryNode(Node n) {
        libraryTree.removeNode(n);
    }

    public void addConfigNode(Node n) {
        configTree.addNode(n);
    }

    public void addConfigLink(Link l) {
        configTree.addLink(l);
    }

    public void removeConfigNode(Node n) {
        configTree.removeNode(n);
    }

    public void removeConfigLink(Link l) {
        configTree.removeLink(l);
    }
}
