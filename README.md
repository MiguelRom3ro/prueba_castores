<h2>Prueba Castores</h2>

<h3>Herramientas y lenguajes utilizados</h3>

<ul> 
  <li>IDE: IntelliJ IDEA 2024.1.4</li>
  <li>Lenguaje: Java JDK 22 con Maven</li>
  <li>Servidor web: Tomcat 10.1.26</li>
  <li>DBMS: XAMPP 8.2.12</li>
</ul>

<h3>Pasos para instalar</h3>
<ol>
  <li>Descargar e instalar IDE IntelliJ IDEA para su sistema operativo </li> 
  <a href="https://www.jetbrains.com/es-es/idea/download/?section=windows"> Descargar IntelliJ IDEA</a>
  <li>Descargar e instalar XAMPP</li>
  <a href="https://www.apachefriends.org/download.html">Descargar XAMPP</a>
  <li>Descargar Tomcat y descomprimir</li>
  <a href="https://tomcat.apache.org/download-10.cgi">Descargar Tomcat</a>
  <li>Descargar y abrir repositorio en IntelliJ IDEA</li>
  <ul>
    <li>Abrimos IntelliJ IDEA.</li>
    <li>Seleccionamos la opcion de "Get from VSC" que esta en la parte superior derecha de la ventana.</li>
    <li>Seleccionamos "Git" en version de control.</li>
    <li>En la opcion de URL agregamos lo siguiente: git@github.com:MiguelRom3ro/prueba_castores.git</li>
    <li>Seleccionamos el directorio donde queremos que se guarde el repositorio localmente.</li>\
    <li>Por ultimo damos click en clone.</li>
  </ul>
  <li>Configurar Tomcat en IntelliJ IDEA</li>
  <ul>
    <li>Dentro de nuestro IDE seleccionaremos la siguiente opcion "Edit configurations...".</li>
    <img src="https://github.com/user-attachments/assets/7c73f3eb-9168-4d06-906e-f305eb300636">
    <li>Agregamos una nueva configuracion dando click en "+".</li>
    <img src="https://github.com/user-attachments/assets/6b8183c3-746a-414a-a2a2-e9df87e38f8b">
    <li>En la seccion de "Tomcat Server" seleccionamos "Local".</li>
    <img src="https://github.com/user-attachments/assets/3d1b74dd-9923-498c-9652-0714ea42bf04">
    <li>Una vez seleccionado nos dirigimos a la sección "Deployment".</li>
    <img src="https://github.com/user-attachments/assets/26236d4a-d29b-4433-a5cd-7927044b0008">
    <li>Damos click en "+" y luego en "Artefacts".</li>
    <img src="https://github.com/user-attachments/assets/255fadee-b7e8-4dee-a167-f60d0e2548c8">
    <li>Seleccionamos "prueba_castores:war explored" y damos click en "Continue".</li>
    <img src="https://github.com/user-attachments/assets/8336cca2-9f9d-42c2-89e8-07476323fef9">
    <li>Regresamos a la seccion "Server" y en la seccion URL agregamos la siguiente ruta: http://localhost:8080/prueba_castores_war_exploded/login.jsp</li>
    <img src="https://github.com/user-attachments/assets/06a5a910-9a60-447f-9759-bface9ae47c8">
    <li>En la opción "JRE" seleccionamos la opcion "22".</li>
    <img src="https://github.com/user-attachments/assets/5bd2e0f4-27ec-4b6a-8236-7fb9d64e84ff">
    <li>Por ultimo damos click en "Apply" y luego en "Ok".</li>
    <img src="https://github.com/user-attachments/assets/6969df0d-b87f-4d8d-8317-1082b110021e">
  </ul>
  <li>Gestionar la base de datos</li>
  <ul>
    <li>Abrimos XAMPP y activamos los servicios "Apache" y "MySQL"</li>
    <img src="https://github.com/user-attachments/assets/f31d231c-3761-4bb9-9a8b-3804a5037856">
    <li>En nuestro navegador nos dirigimos a la siguiente ruta: http://localhost/phpmyadmin/</li>
    <li>En la seccion de SQL agregamos el script que esta en la carpeta "SCRIPTS" en la raiz de este repositorio.</li>
    <img src="https://github.com/user-attachments/assets/b21eaf9e-64a6-4c03-a6a5-ce5e5ae5595e">
    <li>Por ultimo ejecutamos la consulta, esto nos creara la base de datos con todas las tablas y recursos necesarios.</li>
  </ul>
  <li>Conectar el repositorio con la base de datos</li>
  <ul>
    <li>Dentro de nuestro proyecto en el IDE nos dirigimos a la siguiente ruta: src\main\java\com\example\prueba_castores\config\Database.java</li>
    <li>Modificamos las credenciales correspondientes para que se conecte el repositorio con la base de datos.</li>
  </ul>
  <li>Correr el proyecto</li>
  <ul>
    <li>En la seccion de configuraciones vista anteriormente seleccionamos "Tomcat" y damos click en el icono de play.</li>
    <img src="https://github.com/user-attachments/assets/2f050b2d-181b-434d-a916-b5ea6cf43832">
    <li>Se nos abrira una pestaña del navegador con un inicio de sesion las credenciales correspondientes son las siguientes:</li>
    <li>Correo: miguel@example.com , Contraseña: password123</li>
    <li>Correo: luz@example.com , Contraseña: password123</li>
  </ul>
  <li>Listo la aplicacón ya estara funcionando correctamente.</li>
</ol>

