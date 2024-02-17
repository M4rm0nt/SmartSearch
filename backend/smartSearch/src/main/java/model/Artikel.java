package model;

public class Artikel {
    private Long id;
    private String name;
    private String ean;

    public Artikel() {}

    public Artikel(Long id, String name, String ean) {
        this.id = id;
        this.name = name;
        this.ean = ean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
