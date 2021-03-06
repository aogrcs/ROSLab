/**
 *
 */
package roslab.processors.mechanics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import roslab.model.general.Configuration;
import roslab.model.general.Link;
import roslab.model.general.Node;
import roslab.model.mechanics.HWBlock;
import roslab.model.mechanics.HWBlockType;
import roslab.model.mechanics.Joint;
import roslab.processors.ModelProcessor;

/**
 * @author Peter Gebhard
 */
public class HardwareModelProcessor extends ModelProcessor {

    /**
     * @param config
     */
    public HardwareModelProcessor(Configuration config) {
        super(config);
        st = group.getInstanceOf("HWBot");
    }

    /*
     * (non-Javadoc)
     * @see roslab.processors.ModelProcessor#output()
     */
    @Override
    public String output() {
        List<HWBlock> blocks = new ArrayList<HWBlock>();
        for (Node n : config.getNodes()) {
            if (n instanceof HWBlock) {
                blocks.add((HWBlock) n);
            }
        }
        st.setAttribute("add_components", printAddComponentsSection(blocks));
        st.setAttribute("set_sub_parameters", printSetSubParametersSection(blocks));
        st.setAttribute("append_components", printAppendComponentsSection(blocks));
        st.setAttribute("attach_components", printAttachComponentsSection(config.getLinksOfType(Joint.class)));
        st.setAttribute("add_tabs", printAddTabsSection(blocks));
        st.setAttribute("set_parameters", printSetParametersSection(blocks));
        return st.toString();
    }

    private String printAddComponentsSection(List<HWBlock> blocks) {
        String str = "";
        for (HWBlock block : blocks) {
            str += "self.addComponent(\"" + block.getName() + "\", " + block.getType().name() + ", None)\n";
        }
        return str;
    }

    private String printSetSubParametersSection(List<HWBlock> blocks) {
        String str = "";
        for (HWBlock block : blocks) {
            for (Entry<String, String> entry : block.getAnnotations().entrySet()) {
                str += "self.setSubParameter(\"" + block.getName() + "\", \"" + entry.getKey() + "\", " + entry.getValue() + ")\n";
            }
        }
        return str;
    }

    private String printAppendComponentsSection(List<HWBlock> blocks) {
        String str = "";
        for (HWBlock block : blocks) {
            if (block.getType().equals(HWBlockType.Brains)) {
                str += "self.append(\"" + block.getName() + "\", \"core\")\n";
            }
        }
        return str;
    }

    private String printAttachComponentsSection(List<Link> links) {
        String str = "";
        for (Link l : links) {
            HWBlock src = (HWBlock) l.getSrc().getParent();
            HWBlock dest = (HWBlock) l.getDest().getParent();
            String srcPrefix = src.getName();  // Just using the component's own
            // name also as the prefix (are
            // prefixes actually important
            // here?)
            String destPrefix = dest.getName();
            if (src.getType().equals(HWBlockType.Brains)) {
                srcPrefix = "core";
            }
            if (dest.getType().equals(HWBlockType.Brains)) {
                destPrefix = "core";
            }
            str += "self.attach((\"" + src.getName() + "\", \"" + srcPrefix + "\", \"" + l.getSrc().getName() + "\"), (\"" + dest.getName()
                    + "\", \"" + destPrefix + "\", \"" + l.getDest().getName() + "\"), Fold(-180))\n";
        }
        return str;
    }

    private String printAddTabsSection(List<HWBlock> blocks) {
        String str = "";
        Iterator<HWBlock> it = blocks.iterator();
        while (it.hasNext()) {
            HWBlock block = it.next();
            if (!block.getType().equals(HWBlockType.Brains)) {
                str += "self.addTabs((Tab(), \"tab" + block.getName() + "\", 9), (\"" + block.getName() + "\", \"" + block.getName()
                        + "\", \"botedge.2\"), (\"" + block.getName() + "\", \"" + block.getName() + "\", \"topedge.3\")\n";
            }
        }
        return str;
    }

    private String printSetParametersSection(List<HWBlock> blocks) {
        String str = "";
        HWBlock core = null;
        for (HWBlock block : blocks) {
            if (block.getType().equals(HWBlockType.Brains)) {
                core = block;
            }
            if (block.getAnnotation("hardware") != null) {
                str += "f.setParameter(\"" + block.getName() + "\", " + block.getAnnotation("hardware") + ")\n";
            }
        }
        str += "f.setParameter(\"length\", " + core.getAnnotation("length") + ")\n";
        str += "f.setParameter(\"height\", " + core.getAnnotation("height") + ")\n";
        return str;
    }

}
