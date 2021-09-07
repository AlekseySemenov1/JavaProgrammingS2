package ServerPath;

import Messages.EnterRequest;
import Messages.RegisterRequest;
import Messages.CommandRequest;
import Messages.SaltRequest;

import java.io.IOException;
import java.net.SocketAddress;
import java.security.NoSuchAlgorithmException;

public class RequestExecutor implements Runnable{
    private Object object;
    private CommandReader comReader;
    private AuthorizationManager authoManager;
    private SocketAddress address;

    public RequestExecutor(Object object, CommandReader comReader, AuthorizationManager authoManager, SocketAddress address) throws NoSuchAlgorithmException {
        this.object = object;
        this.comReader = comReader;
        this.authoManager = authoManager;
        this.address = address;
    }

    @Override
    public void run() {
        try {
            if (object instanceof CommandRequest) {
                CommandRequest request = (CommandRequest) object;
                comReader.executeCommand(request.getCommand(), request.getArg(), address, request.getUser());
            } else if (object instanceof EnterRequest) {
                EnterRequest request = (EnterRequest) object;
                authoManager.checkPassword(request.getUser().getLogin(), request.getUser().getPassword(), address);
            } else if (object instanceof RegisterRequest) {
                RegisterRequest request = (RegisterRequest) object;
                authoManager.addNewUser(request.getUser(), address);
            } else if (object.equals("connect")) {
                authoManager.setConnection(address);
            } else if (object instanceof SaltRequest) {
                SaltRequest request = (SaltRequest) object;
                authoManager.getSalt(request.getLogin(), address);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
