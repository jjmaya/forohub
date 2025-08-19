Esta API expone un conjunto de endpoints para la gestión de tópicos dentro de un sistema de foros o discusión. 
Permite realizar operaciones CRUD (crear, leer, actualizar y eliminar), además de filtrar tópicos por curso y paginar resultados.

⚡ Características Técnicas

Implementación con Spring Boot.

Uso de Spring Data JPA para persistencia.

Paginación con Pageable.

Uso de DTOs para transferencia de datos.

Validación de entradas con jakarta.validation.

Cache en listado de tópicos (@Cacheable).

📂 Entidades principales

Topico

Curso

Usuario

📌 Controlador Principal: TopicosController

El controlador maneja las peticiones bajo la ruta base:

/topicos

Endpoints disponibles
1. Listar Tópicos
GET /topicos


Retorna una lista paginada de todos los tópicos.

Permite filtrar por curso mediante el parámetro nombreCurso.

Soporta paginación y ordenamiento por fechaCreacion (descendente).

Respuesta: Page<TopicoDTO>

Registrar un Tópico
POST /topicos


Crea un nuevo tópico en la base de datos.

Recibe un objeto RegistrarTopico con la información básica:

título

mensaje

idCurso

idUsuario

Retorna el recurso creado junto con la URL del nuevo tópico.

3. Obtener Detalles de un Tópico
GET /topicos/{id}


Retorna la información detallada de un tópico específico.

Respuesta: DetallesTopicoDTO

Si el tópico no existe, retorna 404 Not Found.

4. Actualizar un Tópico
PUT /topicos/{id}


Actualiza los datos de un tópico existente.

Recibe un objeto ActualizarTopico con los cambios.

Respuesta: TopicoDTO con los datos actualizados.

Si el tópico no existe, retorna 404 Not Found.

5. Eliminar un Tópico
DELETE /topicos/{id}


Elimina un tópico según su ID.

Respuesta: 200 OK si la eliminación fue exitosa, o 404 Not Found si el tópico no existe.


🔒 Seguridad y Autenticación

Esta API está protegida con un sistema de login y tokens.
Eso significa que no cualquiera puede acceder a los endpoints, sino solo los usuarios que estén registrados y tengan un token de acceso válido.

El flujo funciona así:

Login:
El usuario envía su correo y contraseña al endpoint de login.
Si los datos son correctos, el sistema devuelve un token JWT (una especie de “llave digital”).

Token:
Ese token representa que el usuario está autenticado.
Debe copiarse y enviarse en cada petición dentro de los headers, así:

Authorization: Bearer <tu_token_aquí>


Acceso seguro:
Cuando el token está presente y es válido, la API deja acceder a las rutas protegidas (por ejemplo, crear, editar o eliminar un tópico).
Si el token no está o es inválido, la API responde con un error de autorización.

En resumen:

Te logueas una sola vez.

El sistema te da un token.

Usas ese token en todas las demás solicitudes mientras dure su validez.


