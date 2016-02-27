package amerifrance.guideapi.api;

import amerifrance.guideapi.ModInformation;
import amerifrance.guideapi.api.base.Book;
import com.google.gson.GsonBuilder;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;

public class GuideRegistry {

    /**
     * The GsonBuilder used by Guide-API to create Books from Jsons. Access after Pre-Init
     */
    public static GsonBuilder bookBuilder;

    private static List<Book> bookList = new ArrayList<Book>();

    /**
     * If the book does not have a custom model, this will also register a render for the book.
     *
     * @param book  - The book to register
     * @param event - Initialization Phase to load at.
     */
    public static void registerBook(Book book, FMLPreInitializationEvent event) {
        bookList.add(book);

        if (event != null && event.getSide() == Side.CLIENT && !book.isCustomModel())
            ModelLoader.setCustomModelResourceLocation(GuideAPIItems.guideBook, getIndexOf(book), new ModelResourceLocation(new ResourceLocation(ModInformation.TEXLOC + "ItemGuideBook"), "type=book"));
    }

    /**
     * @param index - The index of the book
     *
     * @return the book corresponding to the index given
     */
    public static Book getBook(int index) {
        return bookList.get(index);
    }

    /**
     * @param book - The book of which to get the index
     *
     * @return the index of the book given
     */
    public static int getIndexOf(Book book) {
        return bookList.indexOf(book);
    }

    /**
     * @return the number of registered books
     */
    public static int getSize() {
        return bookList.size();
    }

    /**
     * @return whether or not there are books registered
     */
    public static boolean isEmpty() {
        return bookList.isEmpty();
    }

    /**
     * @param book - The book of which to get the itemstack
     *
     * @return an itemstack corresponding to the ingame book
     */
    public static ItemStack getItemStackForBook(Book book) {
        return new ItemStack(GuideAPIItems.guideBook, 1, getIndexOf(book)).copy();
    }

    /**
     * @return a copy of the booklist
     */
    public static List<Book> getBookList() {
        return new ArrayList<Book>(bookList);
    }
}
