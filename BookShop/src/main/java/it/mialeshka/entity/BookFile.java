package it.mialeshka.entity;

import javax.persistence.*;

@Entity
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBookFile;
    private String nameFile;
    private String extensionFile;
    @ManyToOne (optional=false, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="idBook")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

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
}
