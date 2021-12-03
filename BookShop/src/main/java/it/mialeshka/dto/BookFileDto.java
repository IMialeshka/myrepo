package it.mialeshka.dto;


public class BookFileDto {
    private Long idBookFile;
    private String nameFile;
    private String extensionFile;
    private BookDto book;
 /*   private Long id_book;

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }*/

    public Long getIdBookFile() {
        return idBookFile;
    }

    public void setIdBookFile(Long idBookFile) {
        this.idBookFile = idBookFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getExtensionFile() {
        return extensionFile;
    }

    public void setExtensionFile(String extensionFile) {
        this.extensionFile = extensionFile;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
