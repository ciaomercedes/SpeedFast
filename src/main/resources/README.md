# SpeedFast

---

👤 **Autor del proyecto**
* **Nombre completo:** Mercedes Malandrino
* **Materia:** DESARROLLO ORIENTADO A OBJETOS II_003A
* **Carrera:** Analista Programador Computacional
* **Sede:** Campus En Línea

---

## 📘 Descripción General

**SpeedFast** es una aplicación desarrollada en **Java** que funciona como un sistema de gestión de
pedidos y entregas para la empresa de logística SpeedFast. Su objetivo principal es automatizar el
flujo de pedidos, desde el registro hasta la asignación de repartidores y la confirmación de entregas,
garantizando que la información se almacene correctamente en la base de datos y se mantenga actualizada
en tiempo real.

Esta aplicación combina Programación Orientada a Objetos, Hilos (Threads) para simulación de reparto, y 
conexión JDBC a MySQL, garantizando persistencia y consistencia de datos.

Con esta aplicación, la empresa puede:
- ✔️ Registrar distintos tipos de pedidos: Comida, Encomienda y Compra Express.
- ✔️ Actualizar estados de entrega.
- ✔️ Asignar automáticamente o manualmente repartidores a los pedidos pendientes.
- ✔️ Monitorear el estado de los pedidos: pendiente, en reparto o entregado.
- ✔️ Registrar y almacenar las entregas en la base de datos para auditoría y seguimiento.
- ✔️ Consultar los pedidos existentes mediante una interfaz gráfica amigable.

---
## 🧱 Estructura general del proyecto

```
SpeedFast/
📁 src/
├── main/                   
  └── Main.java                # Clase principal que inicia las ventanas interactivas
  
└── bd/                   
  └── ConexionBD.java          # Clase que gestiona la conexión con la base de datos MySQL Server
  
└── controladores/                   
  └── ControladorDeEnvios.java # Controlador que administra la lista de pedidos, despacho y flujo de entrega
  
└── dao/                   
  └── EntregaDAO.java          # DAO que registra entregas en la base de datos (relación pedido-repartidor)
  └── PedidoDAO.java           # DAO que gestiona inserción, listado y actualización de pedidos en la BD
  └── RepartidorDAO.java       # DAO que obtiene el ID de un repartidor por nombre desde la BD
  
└── modelo/                 
  └── EstadoPedido .java       # Enumeración que define los posibles estados de un pedido (PENDIENTE, EN_REPARTO, ENTREGADO)
  └── Pedido.java              # Superclase abstracta que define atributos y métodos comunes de todos los pedidos
  └── PedidoComida.java        # Clase que representa pedidos de comida, hereda de Pedido
  └── PedidoEncomienda.java    # Clase que representa pedidos de encomienda, hereda de Pedido
  └── PedidoExpress.java       # Clase que representa pedidos express, hereda de Pedido
  └── Repartidor.java          # Clase que representa un repartidor y simula la entrega de pedidos (implementa Runnable)
  └── ZonaDeCarga.java         # Clase que actúa como buffer/cola para almacenar pedidos pendientes
  
└── interfaces/                
  └── Cancelable.java          # Interfaz que define métodos para operaciones cancelables
  └── Depachable.java          # Interfaz que define métodos para objetos que pueden ser despachables
  └── GestorEntidades.java     # Interfaz que define métodos generales para gestión de entidades de negocio
  
└── vista/                   
  └── VentanaAsignarRepartidor.java # Interfaz gráfica para asignar repartidores a pedidos pendientes
  └── VentanaListaPedidos.java      # Interfaz gráfica que muestra todos los pedidos en un JTable
  └── VentanaPrincipal.java         # Ventana principal con menú de acciones de la aplicación
  └── VentanaRegistroPedido.java    # Interfaz gráfica para registrar nuevos pedidos
  
├── resources/                 
  └── README.md                # Archivo manual de uso de la aplicación y documentación de clases
