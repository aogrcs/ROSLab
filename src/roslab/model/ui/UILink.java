/**
 *
 */
package roslab.model.ui;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import roslab.model.general.Link;

/**
 * @author Peter Gebhard
 */
public class UILink extends Line {

    Link link;
    UIEndpoint src;
    UIEndpoint dest;

    // X AND Y position of mouse during actions
    double mousex = 0;
    double mousey = 0;

    /**
     * @param name
     * @param src
     * @param dest
     * @param link
     */
    public UILink(Link link, UIEndpoint src, UIEndpoint dest) {
        super(src.getCenterX(), src.getCenterY(), dest.getCenterX(), dest.getCenterY());
        this.link = link;
        this.src = src;
        this.dest = dest;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.link.getName();
    }

    /**
     * @return the src
     */
    public UIEndpoint getSrc() {
        return src;
    }

    /**
     * @param src
     *            the src to set
     */
    public void setSrc(UIEndpoint src) {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public UIEndpoint getDest() {
        return dest;
    }

    /**
     * @param dest
     *            the dest to set
     */
    public void setDest(UIEndpoint dest) {
        this.dest = dest;
    }

    /**
     * @return the link
     */
    public Link getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(Link link) {
        this.link = link;
    }

    public void setMouse(MouseEvent mouseEvent, UIEndpoint uiEndpoint) {
        if (uiEndpoint == this.src) {
            mousex = this.getStartX() - mouseEvent.getX();
            mousey = this.getStartY() - mouseEvent.getY();
        }
        else {
            mousex = this.getEndX() - mouseEvent.getX();
            mousey = this.getEndY() - mouseEvent.getY();
        }

    }

    public void updateXY(MouseEvent mouseEvent, UIEndpoint uiEndpoint) {
        if (uiEndpoint == this.src) {
            this.setStartX(mouseEvent.getX() + mousex);
            this.setStartY(mouseEvent.getY() + mousey);
        }
        else {
            this.setEndX(mouseEvent.getX() + mousex);
            this.setEndY(mouseEvent.getY() + mousey);
        }
    }
}
