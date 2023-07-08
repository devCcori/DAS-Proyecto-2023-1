--Tabla Logins
insert into Logins values ('test', '1234')

--Tabla de Proveedores
insert into Proveedores values ('ID001', 'Proveedor 1', 'Direccion 1', 31233);
insert into Proveedores values ('ID002', 'Proveedor 2', 'Direccion 2', 123456);
insert into Proveedores values ('ID003', 'Proveedor 3', 'Direccion 3', 0001122);

--Tabla de Empleados
insert into Empleados values ('E001', 'Empleado 1', 123456, 'Correo 1', '2023-07-07');
insert into Empleados values ('E002', 'Empleado 2', 123456, 'Correo 2', '2023-07-07');
insert into Empleados values ('E003', 'Empleado 3', 123456, 'Correo 3', '2023-07-07');

--Tabla de Productos
insert into Productos values ('P001', 'Producto 1', 10, 12, 100, '');
insert into Productos values ('P002', 'Producto 2', 20, 22, 100, '');
insert into Productos values ('P003', 'Producto 3', 30, 32, 100, '');
insert into Productos VALUES ('P004', 'Producto 4', 40, 42, 100, '');


--Tabla de Compra Detalle
insert into Compra_Detalle values ('C0001', 'P001', 10, 100, 1000);
insert into Compra_Detalle values ('C0001', 'P002', 20, 200, 2000);