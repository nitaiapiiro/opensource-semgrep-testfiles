// License: Commons Clause License Condition v1.0[LGPL-2.1-only] 
// https://github.com/semgrep/semgrep-rules/blob/release/problem-based-packs/insecure-transport/java-spring/spring-ftp-request.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionalOnMissingBean;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.apache.commons.net.ftp.FTPFile;

class Bad {
    @Bean
    @ConditionalOnMissingBean
    public SessionFactory<FTPFile> bad1(FtpSessionFactoryProperties properties) {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        // ruleid: java_crypto_rule-SpringFTPRequest
        ftpSessionFactory.setHost("ftp://example.com");
        ftpSessionFactory.setPort(properties.getPort());
        ftpSessionFactory.setUsername(properties.getUsername());
        ftpSessionFactory.setPassword(properties.getPassword());
        ftpSessionFactory.setClientMode(properties.getClientMode().getMode());
        if (properties.getCacheSessions() != null) {
            CachingSessionFactory<FTPFile> csf = new CachingSessionFactory<>(ftpSessionFactory);
            return csf;
        } else {
            return ftpSessionFactory;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionFactory<FTPFile> bad2(FtpSessionFactoryProperties properties) {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        String url = "ftp://example.com";
        // ruleid: java_crypto_rule-SpringFTPRequest
        ftpSessionFactory.setHost(url);
        ftpSessionFactory.setPort(properties.getPort());
        ftpSessionFactory.setUsername(properties.getUsername());
        ftpSessionFactory.setPassword(properties.getPassword());
        ftpSessionFactory.setClientMode(properties.getClientMode().getMode());
        if (properties.getCacheSessions() != null) {
            CachingSessionFactory<FTPFile> csf = new CachingSessionFactory<>(ftpSessionFactory);
            return csf;
        } else {
            return ftpSessionFactory;
        }
    }
}

class Ok {
    @Bean
    @ConditionalOnMissingBean
    public SessionFactory<FTPFile> ok1(FtpSessionFactoryProperties properties) {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        // ok: java_crypto_rule-SpringFTPRequest
        ftpSessionFactory.setHost("sftp://example.com");
        ftpSessionFactory.setPort(properties.getPort());
        ftpSessionFactory.setUsername(properties.getUsername());
        ftpSessionFactory.setPassword(properties.getPassword());
        ftpSessionFactory.setClientMode(properties.getClientMode().getMode());
        if (properties.getCacheSessions() != null) {
            CachingSessionFactory<FTPFile> csf = new CachingSessionFactory<>(ftpSessionFactory);
            return csf;
        } else {
            return ftpSessionFactory;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionFactory<FTPFile> ok2(FtpSessionFactoryProperties properties) {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        String url = "sftp://example.com";
        // ok: java_crypto_rule-SpringFTPRequest
        ftpSessionFactory.setHost(url);
        ftpSessionFactory.setPort(properties.getPort());
        ftpSessionFactory.setUsername(properties.getUsername());
        ftpSessionFactory.setPassword(properties.getPassword());
        ftpSessionFactory.setClientMode(properties.getClientMode().getMode());
        if (properties.getCacheSessions() != null) {
            CachingSessionFactory<FTPFile> csf = new CachingSessionFactory<>(ftpSessionFactory);
            return csf;
        } else {
            return ftpSessionFactory;
        }
    }
}
