package com.khospodarysko.ztree;

import java.util.List;

public class ZTree {
    public List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static class Node {
        String name;
        String icon = "jenkins/testok.gif";
        List<Child> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public static class Child {
            String name;
            String icon = "jenkins/testok.gif";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
