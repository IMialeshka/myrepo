package it.mialeshka.mapper;

import it.mialeshka.dto.BookDto;
import it.mialeshka.dto.RoleDto;
import it.mialeshka.dto.UserShopDto;
import it.mialeshka.entity.Book;
import it.mialeshka.entity.Role;
import it.mialeshka.entity.UserShop;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-10T08:35:07-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_31 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toRoleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );
        roleDto.setUsers( userShopListToUserShopDtoList( role.getUsers() ) );

        return roleDto;
    }

    @Override
    public Role toRoleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );
        role.setUsers( userShopDtoListToUserShopList( roleDto.getUsers() ) );

        return role;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( toRoleToRoleDto( role ) );
        }

        return list1;
    }

    protected List<UserShopDto> userShopListToUserShopDtoList(List<UserShop> list) {
        if ( list == null ) {
            return null;
        }

        List<UserShopDto> list1 = new ArrayList<UserShopDto>( list.size() );
        for ( UserShop userShop : list ) {
            list1.add( userShopToUserShopDto( userShop ) );
        }

        return list1;
    }

    protected BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setUserShopList( userShopListToUserShopDtoList( book.getUserShopList() ) );
        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setWriter( book.getWriter() );
        bookDto.setGenre( book.getGenre() );
        bookDto.setCoverBook( book.getCoverBook() );
        bookDto.setFileName( book.getFileName() );

        return bookDto;
    }

    protected List<BookDto> bookListToBookDtoList(List<Book> list) {
        if ( list == null ) {
            return null;
        }

        List<BookDto> list1 = new ArrayList<BookDto>( list.size() );
        for ( Book book : list ) {
            list1.add( bookToBookDto( book ) );
        }

        return list1;
    }

    protected UserShopDto userShopToUserShopDto(UserShop userShop) {
        if ( userShop == null ) {
            return null;
        }

        UserShopDto userShopDto = new UserShopDto();

        userShopDto.setId( userShop.getId() );
        userShopDto.setUsername( userShop.getUsername() );
        userShopDto.setName( userShop.getName() );
        userShopDto.setPassword( userShop.getPassword() );
        userShopDto.setRoles( roleListToRoleDtoList( userShop.getRoles() ) );
        userShopDto.setBooksList( bookListToBookDtoList( userShop.getBooksList() ) );

        return userShopDto;
    }

    protected List<UserShop> userShopDtoListToUserShopList(List<UserShopDto> list) {
        if ( list == null ) {
            return null;
        }

        List<UserShop> list1 = new ArrayList<UserShop>( list.size() );
        for ( UserShopDto userShopDto : list ) {
            list1.add( userShopDtoToUserShop( userShopDto ) );
        }

        return list1;
    }

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setUserShopList( userShopDtoListToUserShopList( bookDto.getUserShopList() ) );
        book.setId( bookDto.getId() );
        book.setCoverBook( bookDto.getCoverBook() );
        book.setFileName( bookDto.getFileName() );
        book.setName( bookDto.getName() );
        book.setWriter( bookDto.getWriter() );
        book.setGenre( bookDto.getGenre() );

        return book;
    }

    protected List<Book> bookDtoListToBookList(List<BookDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Book> list1 = new ArrayList<Book>( list.size() );
        for ( BookDto bookDto : list ) {
            list1.add( bookDtoToBook( bookDto ) );
        }

        return list1;
    }

    protected List<Role> roleDtoListToRoleList(List<RoleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDto roleDto : list ) {
            list1.add( toRoleDtoToRole( roleDto ) );
        }

        return list1;
    }

    protected UserShop userShopDtoToUserShop(UserShopDto userShopDto) {
        if ( userShopDto == null ) {
            return null;
        }

        UserShop userShop = new UserShop();

        userShop.setId( userShopDto.getId() );
        userShop.setBooksList( bookDtoListToBookList( userShopDto.getBooksList() ) );
        userShop.setRoles( roleDtoListToRoleList( userShopDto.getRoles() ) );
        userShop.setUsername( userShopDto.getUsername() );
        userShop.setName( userShopDto.getName() );
        userShop.setPassword( userShopDto.getPassword() );

        return userShop;
    }
}
