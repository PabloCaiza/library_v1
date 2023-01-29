package com.distribuida.mapper;

import com.distribuida.db.Book;
import io.helidon.dbclient.DbRow;
import java.math.BigDecimal;


public class BookMapper  {

    public static Book map(DbRow row) {
        return Book
                .builder()
                .id(row.column("id").as(Integer.class))
                .title(row.column("title").as(String.class))
                .isbn(row.column("isbn").as(String.class))
                .author(row.column("author_id").as(Integer.class))
                .price(row.column("price").as(BigDecimal.class))
                .build();
    }

}
