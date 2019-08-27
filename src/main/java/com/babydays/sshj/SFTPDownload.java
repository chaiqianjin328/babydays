package com.babydays.sshj;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.IOException;


/** This example demonstrates downloading of a file over SFTP from the SSH server. */
public class SFTPDownload {

    public static void main(String[] args)
            throws IOException {
        final SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();
        ssh.addHostKeyVerifier(new PromiscuousVerifier());
        //ssh.connect("118.145.3.158",22);
        ssh.connect("118.144.82.48",22);
        try {
            //ssh.authPassword("root", "V5TixaSpringLaiFA3Z158");
            ssh.authPassword("root", "W5TixaSpingLaiFA3Z48");
            final SFTPClient sftp = ssh.newSFTPClient();
            try {
            	sftp.get("/opt/resin_web/doc/clientAPK/Enter/tixa.keystore", new FileSystemFile("/Users/chaiqianjin/resin_web/doc/clientAPK/Enter/tixa.keystore"));
            } finally {
                sftp.close();
            }
        } finally {
            ssh.disconnect();
        }
    }

}
