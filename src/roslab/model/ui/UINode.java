/**
 *
 */
package roslab.model.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import roslab.model.general.Endpoint;
import roslab.model.general.Feature;
import roslab.model.general.Node;

/**
 * @author Peter Gebhard
 */
public class UINode extends Group {

    private static final int CHARACTER_SIZE = 7;  // The pixel width of
    // characters in displayed
    // strings
    private static final int DEFAULT_HEIGHT = 80;
    private static final int DEFAULT_WIDTH = 80;
    private static final int ENDPOINT_SIZE = 20;
    private static final double ENDPOINT_Y_OFFSET = 20;
    private static final int UINODE_WIDTH_PADDING = 70;
    private static final double TEXT_Y_OFFSET = 30;

    Node node;
    Rectangle nodeRect;
    Text nodeText;
    List<UIEndpoint> endpoints = new ArrayList<UIEndpoint>();

    // X AND Y position of mouse during selection
    double mousex = 0;
    double mousey = 0;
    double mousexText = 0;
    double mouseyText = 0;

    /**
     * @param node
     * @param endpoints
     * @param xx
     * @param yy
     */
    public UINode(Node node, double xx, double yy) {
        super();
        this.nodeRect = new Rectangle(xx, yy, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.nodeRect.getStyleClass().add(getClass().getSimpleName());
        this.nodeText = new Text(this.nodeRect.getX() + this.nodeRect.getWidth() / 2, this.nodeRect.getY() + TEXT_Y_OFFSET, node.getName());
        this.node = node;
        if (node != null) {
            // Set node-class-specific style
            this.nodeRect.getStyleClass().add(this.node.getClass().getSimpleName());
        }
        if (node instanceof Endpoint) {
            // If the node itself is the endpoint, add it on the left side,
            // halfway down
            this.endpoints
            .add(new UIEndpoint((Endpoint) node, this, this.nodeRect.getX(), this.nodeRect.getY() + this.nodeRect.getHeight() / 2, true));
        }
        else {
            int endpointIndex = 0;
            int endpointCount = node.getFeatures().values().size();
            int longestEndpointNameLeft = 0;
            int longestEndpointNameRight = 0;

            // Find longest endpoint name (to adjust node width to fit endpoint
            // names and the node's name)
            for (String s : node.getFeatures().keySet()) {
                if (endpointIndex < endpointCount / 2) {
                    longestEndpointNameLeft = Math.max(longestEndpointNameLeft, s.length());
                }
                else {
                    longestEndpointNameRight = Math.max(longestEndpointNameRight, s.length());
                }
                endpointIndex++;
            }

            // Check if the UINode is high enough to show all endpoints
            if (this.nodeRect.getWidth() < (longestEndpointNameLeft + longestEndpointNameRight + this.node.getName().length()) * CHARACTER_SIZE
                    + UINODE_WIDTH_PADDING) {
                this.setWidth((longestEndpointNameLeft + longestEndpointNameRight + this.node.getName().length()) * CHARACTER_SIZE
                        + UINODE_WIDTH_PADDING);
            }

            // Check if the UINode is high enough to show all endpoints
            if (this.nodeRect.getHeight() < Math.ceil(endpointCount / 2.0) * ENDPOINT_SIZE + ENDPOINT_Y_OFFSET) {
                this.nodeRect.setHeight(Math.ceil(endpointCount / 2.0) * ENDPOINT_SIZE + ENDPOINT_Y_OFFSET);
            }

            endpointIndex = 0;

            // Create endpoint objects in the appropriate location on the edges
            // of the node
            for (Feature f : node.getFeatures().values()) {
                // put half of the endpoints on the left side
                if (endpointIndex < endpointCount / 2) {
                    this.endpoints.add(new UIEndpoint((Endpoint) f, this, this.nodeRect.getX(), this.nodeRect.getY()
                            + (ENDPOINT_SIZE * endpointIndex) + ENDPOINT_Y_OFFSET, true));
                }
                else {
                    // put the other half on the right side
                    this.endpoints.add(new UIEndpoint((Endpoint) f, this, this.nodeRect.getX() + this.nodeRect.getWidth(), this.nodeRect.getY()
                            + (ENDPOINT_SIZE * (endpointIndex - endpointCount / 2)) + ENDPOINT_Y_OFFSET, false));
                }
                endpointIndex++;
            }
        }

        this.getChildren().addAll(this.nodeRect, this.nodeText);
        this.getChildren().addAll(this.endpoints);

        // THIS IS AN OPTIONAL WAY TO ENABLE DRAGGING WITHOUT USING THE
        // BUILDNODE METHOD
        //
        // // EventListener for MousePressed
        // onMousePressedProperty().set(new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        // // record the current mouse X and Y position on Node
        // mousex = event.getSceneX();
        // mousey = event.getSceneY();
        // // get the x and y position measure from Left-Top
        // x = getLayoutX();
        // y = getLayoutY();
        // }
        // });
        //
        // // Event Listener for MouseDragged
        // onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        // // Get the exact moved X and Y
        // x += event.getSceneX() - mousex;
        // y += event.getSceneY() - mousey;
        //
        // // set the position of Node after calculation
        // setLayoutX(x);
        // setLayoutY(y);
        //
        // // again set current Mouse x AND y position
        // mousex = event.getSceneX();
        // mousey = event.getSceneY();
        // }
        // });
    }

    private void setWidth(int i) {
        this.nodeRect.setWidth(i);
        this.nodeText.setX(this.nodeRect.getX() + (this.nodeRect.getWidth() - this.nodeText.getText().length() * CHARACTER_SIZE) / 2);

    }

    public static UINode buildUINode(Node node, double x, double y) {
        final UINode n = new UINode(node, x, y);
        n.nodeRect.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                n.mousex = n.nodeRect.getX() - mouseEvent.getX();
                n.mousey = n.nodeRect.getY() - mouseEvent.getY();
                n.mousexText = n.nodeText.getX() - mouseEvent.getX();
                n.mouseyText = n.nodeText.getY() - mouseEvent.getY();
                for (UIEndpoint e : n.endpoints) {
                    e.setMouse(mouseEvent);
                }
                n.setCursor(Cursor.CLOSED_HAND);
            }
        });
        n.nodeRect.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                n.setCursor(Cursor.OPEN_HAND);
            }
        });
        n.nodeRect.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                n.nodeRect.setX(mouseEvent.getX() + n.mousex);
                n.nodeRect.setY(mouseEvent.getY() + n.mousey);
                n.nodeText.setX(mouseEvent.getX() + n.mousexText);
                n.nodeText.setY(mouseEvent.getY() + n.mouseyText);
                for (UIEndpoint e : n.endpoints) {
                    e.updateXY(mouseEvent);
                }
            }
        });
        n.nodeRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    n.setCursor(Cursor.OPEN_HAND);
                }
            }
        });
        n.nodeRect.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    n.setCursor(Cursor.DEFAULT);
                }
            }
        });
        return n;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.node.getName();
    }

    /**
     * @return the endpoints
     */
    public List<UIEndpoint> getUIEndpoints() {
        return endpoints;
    }

    /**
     * @param endpoints
     *            the endpoints to set
     */
    public void setUIEndpoints(List<UIEndpoint> endpoints) {
        this.endpoints = endpoints;
    }

    /**
     * @return an endpoint
     */
    public UIEndpoint getUIEndpoint(Endpoint e) {
        for (UIEndpoint u : endpoints) {
            if (u.endpoint == e) {
                return u;
            }
        }
        return null;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node
     *            the node to set
     */
    public void setNode(Node node) {
        this.node = node;
    }

}
