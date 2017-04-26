import com.querydsl.sql.codegen.MetaDataExporter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.postgresql.Driver

class QueryDslTask extends DefaultTask {
    Class driverClass = Driver
    String url = 'jdbc:postgresql://localhost:5432/sen'
    String username = 'postgres'
    String password = ''
    String packageName = 'com.westernacher.tutorial.querydsl'
    File destDir = project.file("${project.buildDir}/querydsl")

    QueryDslTask() {
        description = 'create querydsl classes'
        group = 'build setup'
    }

    @TaskAction
    void generate() {
        def driver = driverClass.newInstance()
        def connection = driver.connect(url, driverProperties())

        def exporter = new MetaDataExporter()
        exporter.setPackageName(packageName)
        exporter.setTargetFolder(destDir)

        exporter.export(connection.getMetaData())
    }

    Properties driverProperties() {
        [user: username, password: password]
    }
}
