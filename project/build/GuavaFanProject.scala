import sbt._

class GuavaFanProject(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {

  val guava = "com.google.guava" % "guava" % "r07"
  
}
