package it.mialeshka.dto;


import java.util.List;


public class BookDto {
    private Long id;
    private String name;
    private String writer;
    private String genre;
    private String coverBook;
    private String fileName;
    private List<UserShopDto> userShopList;

    public List<UserShopDto> getUserShopList() {
        return userShopList;
    }

    public void setUserShopList(List<UserShopDto> userShopList) {
        this.userShopList = userShopList;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverBook() {
        return coverBook;
    }

    public void setCoverBook(String coverBook) {
        this.coverBook = coverBook;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
