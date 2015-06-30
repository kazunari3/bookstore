# bookstore

* create tables  
`$su - cnet`  
`$psql -d testdb -U cent -f ./newfile.sql`  
* insert t_book data  
`#psql testdb`    
`testdb-> insert into t_book(isbn,title,author, publisher,price) values ('9898-998', '坊っちゃん', '漱石', 'A出', 450);`
* open URL http://localhost:8080/bookstore/index.jsp
![代替テキスト](https://github.com/kazunari3/bookstore/blob/master/index.png "画像タイトル")

Eclipse Java EE IDE for Web Developers.

Version: Mars Release (4.5.0)
Build id: 20150621-1200
