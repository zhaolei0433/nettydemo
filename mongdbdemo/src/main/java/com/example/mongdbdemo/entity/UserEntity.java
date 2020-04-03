package com.example.mongdbdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "collection_machine_disks")
public class UserEntity implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;

        @Field("msn")
        private String msn;

        @Field("disks")
        public List<String> disks;

        @Field("time_service_start")
        public Integer timeServiceStart;

        @Field("time_service_end")
        public Integer timeServiceEnd;


}
