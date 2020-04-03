package com.example.mongdbdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author zhaolei
 * Create: 2019/1/28 11:35
 * Modified By:
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "second_mongo")
public class SecondEntity implements Serializable {

    private ObjectId id;
    private String value ;

    @Override
    public String toString() {
        return "SecondEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
