pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
        stage('Clone') {
            steps {
                timeout(time: 2, unit: 'MINUTES'){
                    git branch: 'main', credentialsId: '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', url: 'https://github.com/haelMC/trabajitos.git'
                }
            }
        }
        stage('Build') {
            steps {
                timeout(time: 8, unit: 'MINUTES'){
		    // sh "mvn clean package -f SysAsistenciaAn/pom.xml"
                    sh "mvn -DskipTests clean package -f pom.xml"
                }
            }
        }
        stage('Test') {
            steps {
                timeout(time: 8, unit: 'MINUTES'){
                    // Se cambia <testsss> por <install> para que se genere el reporte de jacoco
                    sh "mvn clean install -f pom.xml"
                }
            }
        }
        stage('Sonar') {
            steps {
                timeout(time: 4, unit: 'MINUTES'){
                    withSonarQubeEnv('Sonarqube'){
                        sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Pcoverage -f pom.xml"
                    }
                }
            }
        }
        stage('Quality gate') {
            steps {

                sleep(10) //seconds

                timeout(time: 2, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy') {
            steps {
			    timeout(time: 8, unit: 'MINUTES'){
					// Ejecutar mvn spring-boot:run
					echo "mvn spring-boot:run -f pom.xml"
                }
                //echo "mvn spring-boot:run -f SysAsistenciaAn/pom.xml"
                 //echo "mvn spring-boot:run -f SysAsistenciaAn/pom.xml"
            }
        }
    }
}
