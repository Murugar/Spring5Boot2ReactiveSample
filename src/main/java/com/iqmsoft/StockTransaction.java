package com.iqmsoft;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockTransaction {
    String user;
    Stock stock;
    Date when;
}
