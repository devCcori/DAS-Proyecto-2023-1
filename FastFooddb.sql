
USE master
go

DROP DATABASE FastFood
go

CREATE DATABASE FastFood
go

USE FastFood
go

CREATE TABLE Carrete_Pedidos
( 
	ID_Venta             varchar(20)  NOT NULL ,
	TimeStamp            datetime2(0)  NOT NULL ,
	Estado               varchar(20)  NOT NULL 
)
go



ALTER TABLE Carrete_Pedidos
	ADD CONSTRAINT XPKCarrete_Pedidos PRIMARY KEY  CLUSTERED (ID_Venta ASC)
go



CREATE TABLE Cliente
( 
	ID_Cliente           varchar(20)  NOT NULL ,
	Nombre               char(18)  NOT NULL ,
	Apellido             char(18)  NULL ,
	Cumpleaños           datetime  NULL 
)
go



ALTER TABLE Cliente
	ADD CONSTRAINT XPKCliente PRIMARY KEY  CLUSTERED (ID_Cliente ASC)
go



CREATE TABLE Compra_Detalle
( 
	ID_Producto          varchar(20)  NOT NULL ,
	Cantidad             decimal(10,2)  NULL ,
	Coste_Unitario       decimal(10,2)  NULL ,
	Total                decimal(10,2)  NOT NULL ,
	Cod_Compra           varchar(20)  NULL 
)
go



CREATE TABLE Compra_Total
( 
	Fecha                datetime2(0)  NOT NULL ,
	Cod_Compra           varchar(20)  NOT NULL ,
	Total                decimal(10,2)  NOT NULL ,
	ID_Proveedor         varchar(20)  NOT NULL ,
	ID_Empleado          varchar(20)  NOT NULL 
)
go



ALTER TABLE Compra_Total
	ADD CONSTRAINT XPKCompra_Total PRIMARY KEY  CLUSTERED (Cod_Compra ASC)
go



CREATE TABLE Empleados
( 
	ID_Empleado          varchar(20)  NOT NULL ,
	Nombre               varchar(20)  NOT NULL ,
	Telefono             int  NULL ,
	Correo               varchar(20)  NULL ,
	Fecha_Cumple         date  NULL 
)
go



ALTER TABLE Empleados
	ADD CONSTRAINT XPKEmpleados PRIMARY KEY  CLUSTERED (ID_Empleado ASC)
go



CREATE TABLE Logins
( 
	Username             varchar(20)  NOT NULL ,
	Password             varchar(20)  NOT NULL 
)
go



ALTER TABLE Logins
	ADD CONSTRAINT XPKLogins PRIMARY KEY  CLUSTERED (Username ASC)
go



CREATE TABLE Productos
( 
	ID_Producto          varchar(20)  NOT NULL ,
	Nombre_Producto      varchar(20)  NOT NULL ,
	Costo                decimal(10,2)  NOT NULL ,
	Precio_Venta         decimal(10,2)  NOT NULL ,
	Stock                decimal(10,2)  NULL 
)
go



ALTER TABLE Productos
	ADD CONSTRAINT XPKProductos PRIMARY KEY  CLUSTERED (ID_Producto ASC)
go



CREATE TABLE Proveedores
( 
	ID_Proveedor         varchar(20)  NOT NULL ,
	Nombre_Proveedor     varchar(40)  NOT NULL ,
	RUC_RS               varchar(20)  NULL ,
	Telefono             int  NULL 
)
go



ALTER TABLE Proveedores
	ADD CONSTRAINT XPKProveedores PRIMARY KEY  CLUSTERED (ID_Proveedor ASC)
go



CREATE TABLE Receta
( 
	Precio_Venta         decimal(10,2)  NOT NULL ,
	Nombre               varchar(40)  NOT NULL ,
	ID_Receta            varchar(20)  NULL 
)
go



CREATE TABLE Receta_Insumo
( 
	ID_Recetas           varchar(20)  NOT NULL ,
	Cantidad             decimal(10,2)  NOT NULL ,
	Unidad               varchar(10)  NOT NULL ,
	ID_Producto          varchar(20)  NULL 
)
go



CREATE TABLE Ventas
( 
	ID_Venta             varchar(20)  NOT NULL ,
	TimeStamp            datetime2(0)  NOT NULL ,
	ID_Empleado          varchar(20)  NOT NULL ,
	Total                decimal(10,2)  NOT NULL ,
	Metodo_pago          varchar(20)  NOT NULL ,
	ID_Cliente           varchar(20)  NOT NULL 
)
go



ALTER TABLE Ventas
	ADD CONSTRAINT XPKVentas PRIMARY KEY  CLUSTERED (ID_Venta ASC)
go



CREATE TABLE Ventas_Detalle
( 
	ID_Venta             varchar(20)  NOT NULL ,
	Cantidad             int  NOT NULL ,
	Total                decimal(10,2)  NOT NULL ,
	ID_Receta            varchar(20)  NULL 
)
go




ALTER TABLE Carrete_Pedidos
	ADD CONSTRAINT R_21 FOREIGN KEY (ID_Venta) REFERENCES Ventas(ID_Venta)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Compra_Detalle
	ADD CONSTRAINT R_6 FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Compra_Detalle
	ADD CONSTRAINT R_30 FOREIGN KEY (Cod_Compra) REFERENCES Compra_Total(Cod_Compra)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Compra_Total
	ADD CONSTRAINT R_5 FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Compra_Total
	ADD CONSTRAINT R_25 FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Receta_Insumo
	ADD CONSTRAINT R_23 FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Ventas
	ADD CONSTRAINT R_22 FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Ventas
	ADD CONSTRAINT R_34 FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE Ventas_Detalle
	ADD CONSTRAINT R_14 FOREIGN KEY (ID_Venta) REFERENCES Ventas(ID_Venta)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go


