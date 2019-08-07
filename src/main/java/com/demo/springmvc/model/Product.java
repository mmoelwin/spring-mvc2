package com.demo.springmvc.model;

import com.demo.springmvc.Validation.GreaterThanTen;
import com.demo.springmvc.config.BeanUtil;
import lombok.Data;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Entity
@Data
@XmlRootElement
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private double price;
    @GreaterThanTen(message = "Quantity must be greater than 10!")
    private int quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;
    @ManyToOne
    private Category category;

    public String getPrettyTime(){
        ZoneId zoneId= ZoneId.systemDefault();

        PrettyTime prettyTime= (PrettyTime) BeanUtil.getBeans(PrettyTime.class);

        //return prettyTime.format(new Date(System.currentTimeMillis() + 1000*60*10));
        return prettyTime.format(Date.from(lastUpdated.atStartOfDay(zoneId).toInstant()));
    }
}
