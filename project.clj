(defproject flac-tagmap "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.0.3"]
                 [com.taoensso/timbre "4.4.0"]
                 [net.jthink/jaudiotagger "2.2.3"]]
  :repositories {"bintray" "https://dl.bintray.com/ijabz/maven"}
  :plugins [[lein-environ "1.0.3"]
            [lein-pprint "1.1.1"]]
  :main ^:skip-aot flac-tagmap.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
