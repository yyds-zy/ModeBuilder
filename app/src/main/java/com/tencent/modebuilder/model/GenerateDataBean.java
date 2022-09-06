package com.tencent.modebuilder.model;

import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.util.List;

@JSONType(orders={"id","text","entities","relations"})
public class GenerateDataBean implements Serializable {


    /**
     * id : 0
     * text : 你好小微阿美国的上任总统
     * entities : [{"id":0,"label":"核心问法","start_offset":5,"end_offset":12}]
     * relations : []
     */

    private int id;
    private String text;
    private List<EntitiesBean> entities;
    private List<?> relations;

    public GenerateDataBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<EntitiesBean> getEntities() {
        return entities;
    }

    public void setEntities(List<EntitiesBean> entities) {
        this.entities = entities;
    }

    public List<?> getRelations() {
        return relations;
    }

    public void setRelations(List<?> relations) {
        this.relations = relations;
    }

    @JSONType(orders={"id","label","start_offset","end_offset"})
    public static class EntitiesBean implements Serializable {
        /**
         * id : 0
         * label : 核心问法
         * start_offset : 5
         * end_offset : 12
         */

        private int id;
        private String label;
        private int start_offset;
        private int end_offset;

        public EntitiesBean() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getStart_offset() {
            return start_offset;
        }

        public void setStart_offset(int start_offset) {
            this.start_offset = start_offset;
        }

        public int getEnd_offset() {
            return end_offset;
        }

        public void setEnd_offset(int end_offset) {
            this.end_offset = end_offset;
        }
    }
}
