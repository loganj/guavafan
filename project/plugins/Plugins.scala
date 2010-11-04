import sbt._
 
class Plugins(info: ProjectInfo) extends PluginDefinition(info) {

// for generating idea project files; http://github.com/mpeltonen/sbt-idea-plugin
  val sbtIdeaRepo = "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
  val sbtIdea = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.1-SNAPSHOT"
}
