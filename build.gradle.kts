plugins {
  id("java")
  id("war")
}

group = "cn.it.web.bookforum"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}



tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

dependencies {
  compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
  implementation("org.postgresql:postgresql:42.7.3")
  implementation("com.google.code.gson:gson:2.11.0")
  implementation("commons-fileupload:commons-fileupload:1.5")
  implementation("commons-io:commons-io:2.16.1")
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
  useJUnitPlatform()
}
