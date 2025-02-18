# Antivirus Application

## 📌 Descripción
Antivirus desarrollado en Java con JavaFX que permite analizar archivos para detectar virus mediante firmas de virus predefinidas. También permite al usuario agregar nuevas firmas de virus para futuras detecciones.

## ⚙️ Funcionalidades
- 📂 Cargar un archivo y visualizar su contenido en bytes.
- 🔍 Analizar un archivo para detectar virus conocidos.
- 💾 Guardar nuevas firmas de virus en el programa.
- 🛡️ Indicar si un archivo está infectado y qué virus contiene.

## 🚀 Instalación y Uso
### 1️⃣ Requisitos
- Java 11 o superior
- JavaFX 17+
- Maven o Gradle para gestión de dependencias

### 2️⃣ Clonar el repositorio
```sh
 git clone https://github.com/spectuu/Byte-Analyzer
```

### 3️⃣ Compilar y ejecutar
```sh
mvn clean install
java -jar target/antivirus.jar
```

## 📁 Estructura del Proyecto
```
|-- src/
    |-- main/
        |-- java/
            |-- spectu/antivirus/
                |-- AntivirusApp.java
                |-- AntivirusController.java
                |-- component/
                    |-- FileComponent.java
                |-- resource/
                    |-- ResourceManager.java
                    |-- data/
                        |-- VirusData.java
                    |-- json/
                        |-- VirusJson.java
        |-- resources/
            |-- antivirus.fxml
            |-- virus.json
```

## 🛠️ Clases Principales
- **`AntivirusApp`**: Inicia la aplicación y configura la UI.
- **`AntivirusController`**: Maneja la interacción del usuario con la interfaz.
- **`FileComponent`**: Analiza los archivos y detecta virus.
- **`ResourceManager`**: Gestiona las firmas de virus almacenadas.
- **`VirusData`**: Representa una firma de virus.
- **`VirusJson`**: Permite la manipulación de firmas en formato JSON.

## 📜 Licencia
Este proyecto está bajo la licencia MIT.

## 👥 Autor
- **Santiago Martinez**

