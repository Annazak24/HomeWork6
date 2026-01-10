package dto;

import java.time.LocalDate;

import org.openqa.selenium.WebElement;

public class CourseInfo {

    private String name;
    private LocalDate date;
    private Integer price;

    public CourseInfo(String name, LocalDate date, WebElement element) {
        this.name = name;
        this.date = date;
    }

    public CourseInfo(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getPrice() {
        return price;
    }
}

