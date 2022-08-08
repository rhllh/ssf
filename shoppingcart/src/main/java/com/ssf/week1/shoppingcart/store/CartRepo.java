package com.ssf.week1.shoppingcart.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ssf.week1.shoppingcart.model.Cart;
import com.ssf.week1.shoppingcart.model.CartItem;
import static com.ssf.week1.shoppingcart.util.IOUtil.*;

@Component("cartRepo")
public class CartRepo {
    private static final Logger logger = LoggerFactory.getLogger(CartRepo.class);
    
    private CopyOnWriteArrayList<CartItem> contents = new CopyOnWriteArrayList<>();
    private String username;
    private File file;

    public CartRepo() {

    }

    public CartRepo(String username, String dataDir) {
        this.username = username;
        this.file = new File(dataDir);
    }

    public CopyOnWriteArrayList<CartItem> getContents() {
        return contents;
    }

    public void setContents(CopyOnWriteArrayList<CartItem> contents) {
        this.contents = contents;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public synchronized Cart load() {
        String cartName = this.username + ".cart";
        Cart cart = new Cart(this.username);
        if (file.listFiles() == null) {
            createDir(file.getPath());
        }
        for (File cartFile : file.listFiles()) {
            if (cartFile.getName().equals(cartName)) {
                try {
                    InputStream is = new FileInputStream(cartFile);
                    cart.load(is);
                    is.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return cart;
    }

    public void add(CartItem item) {
        for (CartItem i : contents) {
            if (i.getItemName().equals(item.getItemName())) {
                return;
            }
        }
        contents.add(item);
    }

    public void delete(String cartId, Cart c) {
        int index = 0;
        CopyOnWriteArrayList<CartItem> contentToDelete = c.getCartItems();
        for (CartItem item: contentToDelete) {
            if (item.getId().equals(cartId)) {
                contentToDelete.remove(index);
                break;
            }
            index++;
        }
        c.setCartItems(contentToDelete);
        this.save(c, false);
    }

    public void save(OutputStream os, CopyOnWriteArrayList<CartItem> items) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        for (CartItem item : items) {
            bw.write(item.getItemName() + "," + item.getItemQuantity() + "," + item.getId() + "\n");
        }
        osw.flush();
        bw.flush();
        bw.close();
        osw.close();
    }

    public synchronized void save(Cart cart) {
        this.save(cart,false);
    }

    public synchronized void save(Cart cart, boolean isDel) {
        String cartName = cart.getUsername() + ".cart";
        String saveLoc = file.getPath() + File.separator + cartName;
        File saveFile = new File(saveLoc);
        OutputStream os = null;
        try {
            if (!saveFile.exists()) {
                Path path = Paths.get(file.getPath());
                Files.createDirectories(path);
                saveFile.createNewFile();
            }

            if (isDel) {
                os = new FileOutputStream(saveLoc, true);
            } else {
                os = new FileOutputStream(saveLoc, false);
            }

            this.save(os, cart.getCartItems());
            os.flush();
            os.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
