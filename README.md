# bookstore

* create tables  
`$su - cnet`  
`$psql -d testdb -U cent -f ./newfile.sql`  
* insert t_book data
`#psql testdb`    
`insert into t_book(isbn,title,author, publisher,price) values ('9898-998', '坊っちゃん', '漱石', 'A出', 450);`
* open URL http://localhost:8080/bookstore/index.jsp
