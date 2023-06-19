
package com.javapersistence.part11.repositories;

public interface LogRepositoryCustom {

    void log(String message);

    void showLogs();

    void addSeparateLogsNotSupported();

    void addSeparateLogsSupports();
}
