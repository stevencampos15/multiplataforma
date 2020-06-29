create database pizzeriaDSA;
use pizzeriaDSA;

create table tipoUsuarios(
idTipoUsuario int primary key not null auto_increment,
nombreTipoUsuario varchar(50) not null
);

create table usuarios(
idUsuario int primary key not null AUTO_INCREMENT,
username varchar(100),
pwd varchar(100),
nombreCliente varchar(45) not null,
apellidoCliente varchar(45) not null,
documento varchar(45) not null,
idTipoUsuario int not null,
foreign key (idTipoUsuario) references tipoUsuarios(idTipoUsuario)
);

create table tipoItems(
idTipoItem int not null primary key auto_increment,
nombreTipoItem varchar(50) not null
);

create table items(
idItem int not null primary key auto_increment,
nombreItem varchar(100) not null,
tamano varchar(45) not null,
precio float(10,2) not null,
descripcion varchar(150),
idTipoItem int not null,
foreign key (idTipoItem) references tipoItems(idTipoItem)
);

create table pedidos(
idPedido int primary key not null auto_increment,
idUsuario int not null,
fecha datetime not null,
foreign key (idUsuario) references usuarios(idUsuario)
);

create table detallesPedido(
idPedido int not null,
idDetalle int not null AUTO_INCREMENT primary key,
idItem int not null,
cantidad int not null,
foreign key (idPedido) references pedidos(idPedido) ON DELETE CASCADE,
foreign key(idItem) references items(idItem)
);

insert into tipoUsuarios values(null,"Admin");
insert into tipoUsuarios values(null,"Cliente");

insert into usuarios values (null, "admin", MD5("admin"), "steven","campos","12345",1);

insert into tipoItems values(null, "Pizza");
insert into tipoItems values(null, "Bebidas"); 

insert into items values(null, "Pizza de Jamon", "Familiar", 10.00, "Pizza de 12 porciones",1); 
insert into items values(null, "Pizza de Queso", "Personal", 3.99, "Pizza de 4 porciones",1);

insert into pedidos values(null,1,'2020-05-20');
insert into detallesPedido values(1,null,1, 2);
insert into detallesPedido values(1,null,2, 2);
\
select * from pedidos;

select * from items;