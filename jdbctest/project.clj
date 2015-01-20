(defproject jdbctest "0.1.0-SNAPSHOT"
  :description "Simple Java JDBC test"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main jdbctest.core
  :warn-on-reflection true
  :aot :all
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.1"]
                 [clj-bonecp-url "0.1.1"]
                 [org.slf4j/slf4j-nop "1.7.2"]
                 [mysql/mysql-connector-java "5.1.25"]
                 ])
