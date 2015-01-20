(ns jdbctest.core
    (:require [clojure.java.jdbc :as sql]
              [jdbc.pool.c3p0 :as c3p0]
              [clj-bonecp-url.core :as bone]
              )
    (:gen-class))

(def db-spec
    { :classname "com.mysql.jdbc.Driver"
      :subprotocol "mysql"
      :subname "//host:port/dbname"
      :user "username"
      :password "password"
      :serverTimezone "UTC"
      :initial-pool-size 3
      :useLegacyDatetimeCode false})

(def bone-ds
  {:datasource
   (bone/make-datasource
    {
        :classname (:classname db-spec)
        :jdbc-url (str "jdbc:mysql:" (:subname db-spec))
        :username (:user db-spec)
        :password (:password db-spec)
     })})

(def pool (delay (c3p0/make-datasource-spec db-spec)))

(defn db [type]
  (case type
    "c3p0" @pool
    "bone" bone-ds
    "none" db-spec
    db-spec))


(defn -main [& args]
  (println "Enter character to start...")
  (read)
  (println "Starting...")
  (let [conn (db (first args))]
    (time (sql/query conn ["select * from channel where id = ?" "UCVtFOytbRpEvzLjvqGG5gxQ"]))
    (time (sql/update! conn :channel {:views 100 :title "Test"} ["id = ?" "UCVtFOytbRpEvzLjvqGG5gxQ"])))
  (println "Finished")
  (read))
