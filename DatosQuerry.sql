use FastFood
go

--Tabla Logins
insert into Logins (Username, Password)
VALUES
('test', '1234')
GO

--Tabla de Proveedores
insert into Proveedores (ID_Proveedor, Nombre_Proveedor, RUC_RS, Telefono)
values
('ID001', 'Proveedor 1', 'Direccion 1', 31233),
('ID002', 'Proveedor 2', 'Direccion 2', 123456),
('ID003', 'Proveedor 3', 'Direccion 3', 0001122)
GO

--Tabla de Empleados
insert into Empleados (ID_Empleado, Nombre, Telefono, Correo, Fecha_Cumple)
VALUES
('E001', 'Empleado 1', 123456, 'Correo 1', '2023-07-07'),
('E002', 'Empleado 2', 123456, 'Correo 2', '2023-07-07'),
('E003', 'Empleado 3', 123456, 'Correo 3', '2023-07-07')
go

--Tabla de Productos
INSERT INTO Productos (ID_Producto, Nombre_Producto, Costo, Precio_Venta, Stock)
VALUES 
('P001', 'Pollo crispy', 20, 24, 79),
('P002', 'Salchipapa', 15, 18, 2),
('P003', 'Burger chicken', 15, 18, 42),
('P004', 'Burguer clasica meat', 15, 18, 82),
('P005', 'Choripan', 12, 14.4, 94),
('P006', 'Gaseosa', 4.5, 5.4, 85),
('P007', 'Agua', 3, 3.6, 33),
('P008', 'Vaso de chicha', 3, 3.6, 73),
('P009', 'Vaso de maracuya', 3, 3.6, 97)
GO


--Tabla de Compra Detalle
insert into Compra_Detalle (Cod_Compra, ID_Producto, Cantidad, Coste_Unitario, Total)
VALUES
('C0001', 'P001', 10, 100, 1000),
('C0001', 'P002', 20, 200, 2000),
('C0002', 'P003', 30, 300, 3000),
('C0002', 'P004', 40, 400, 4000),
('C0003', 'P005', 50, 500, 5000),
('C0003', 'P006', 60, 600, 6000),
('C0003', 'P007', 70, 700, 7000),
('C0003', 'P008', 80, 800, 8000),
('C0003', 'P009', 90, 900, 9000)
GO

--Tabla de Receta
insert into Receta (Precio_Venta,Nombre,ID_Receta)
VALUES
(22.3,'Receta 1','R001'),
(32.3,'Receta 2','R002'),
(123,'Receta 3','R003'),
(2.5,'Receta 4','R004'),
(22,'Receta 5','R005'),
(22.3,'Receta 6','R006'),
(32.3,'Receta 7','R007'),
(45.3,'Receta 8','R008'),
(3.3,'Receta 9','R009')
GO

--Tabla de Receta_Insumo
insert into Receta_Insumo (ID_Recetas, Cantidad,Unidad,ID_Producto)
VALUES
('R001', 1,'Kg','P001'),
('R001', 2,'Kg','P002'),
('R002', 3,'Kg','P003'),
('R002', 4,'Kg','P004'),
('R003', 1,'Kg','P005'),
('R003', 2,'Kg','P006'),
('R003', 3,'Kg','P007'),
('R003', 3,'Kg','P008'),
('R003', 4,'Kg','P009')

--Tabla de Clientes
insert into Cliente (ID_Cliente, Nombre, Apellido, Cumpleaños)
VALUES
('C001', 'Cliente 1', 'Apellido 1', '2023-07-07'),
('C002', 'Cliente 2', 'Apellido 2', '2023-07-07'),
('C003', 'Cliente 3', 'Apellido 3', '2023-07-07'),
('C004', 'Cliente 4', 'Apellido 4', '2023-07-07'),
('C005', 'Cliente 5', 'Apellido 5', '2023-07-07'),
('C006', 'Cliente 6', 'Apellido 6', '2023-07-07'),
('C007', 'Cliente 7', 'Apellido 7', '2023-07-07'),
('C008', 'Cliente 8', 'Apellido 8', '2023-07-07'),
('C009', 'Cliente 9', 'Apellido 9', '2023-07-07')
GO

