package org.example.es.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ecommerce")
@Data
public class ESInfo {

        @Id
        private String id;

        private String name;

        private String desc;

        private int price;

        private String producer;

        private String[] tags;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDesc() {
                return desc;
        }

        public void setDesc(String desc) {
                this.desc = desc;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }

        public String getProducer() {
                return producer;
        }

        public void setProducer(String producer) {
                this.producer = producer;
        }

        public String[] getTags() {
                return tags;
        }

        public void setTags(String[] tags) {
                this.tags = tags;
        }
}
