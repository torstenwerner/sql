import org.gradle.api.tasks.Exec

class AsciidoctorTask extends Exec {

    AsciidoctorTask() {
        description = 'convert README into HTML format'
        group = 'documentation'

        commandLine '/usr/local/bin/asciidoctor', '-D', 'docs', 'README.adoc'
    }
}
