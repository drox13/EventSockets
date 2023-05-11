# EventSockets
el sistema usa sockets para comunicar un Administrador que es capas de crear eventos, los cuales se registran en el servidor, existen multiples clientes que se conectar para ver los eventos y poder comprar las voletas del mismo.
1. cuando se crean los eventos por parte del administrador esto se le notifica a los clientes conectados y se les actualiza la interfaz mostrando el nuevo evento
2. cuando un cliente compra una voleta y un segundo cliente desea comprar esa misama voleta (esta validado para que la transaccion falle)
3. El administrador es capaz de ver cuales son las voletas que se han vendido
