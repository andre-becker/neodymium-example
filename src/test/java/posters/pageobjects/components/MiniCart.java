/**
 * 
 */
package posters.pageobjects.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Context;

import posters.dataobjects.Product;
import posters.pageobjects.pages.checkout.CartPage;

/**
 * @author pfotenhauer
 */
public class MiniCart extends AbstractComponent
{
    private SelenideElement headerCart = $("#headerCartOverview");

    private SelenideElement miniCart = $("#miniCartMenu");

    private SelenideElement subOrderPrice = miniCart.find(".subOrderPrice");

    private SelenideElement totalCountElement = $("#btnCartOverviewForm .headerCartProductCount");

    public void isComponentAvailable()
    {
        $("#btnCartOverviewForm").should(exist);
    }

    public void openMiniCart()
    {
        // Click the mini cart icon
        headerCart.scrollTo().click();
        // Wait for mini cart to appear
        // Wait for the mini cart to show
        miniCart.waitUntil(visible, Context.get().configuration.timeout());
    }

    public void closeMiniCart()
    {
        // Click the mini cart icon again
        headerCart.scrollTo().click();
        // Move the mouse out of the area
        $("a#brand").hover();
        // Wait for mini cart to disappear
        // Wait for the mini cart to disappear
        miniCart.waitUntil(not(visible), Context.get().configuration.timeout());
    }

    public CartPage openCartPage()
    {
        // Open the cart
        // Click on the button to go to the Cart
        miniCart.find(".goToCart").scrollTo().click();
        return new CartPage();
    }

    public int getTotalCount()
    {
        return Integer.parseInt(totalCountElement.text());
    }

    public void validateTotalCount(int totalCount)
    {
        totalCountElement.shouldHave(exactText(Integer.toString(totalCount)));
    }

    public String getSubtotal()
    {

        // Store the mini cart subtotal
        // Open mini cart
        openMiniCart();
        // Store subtotal in oldSubTotal
        String subtotal = subOrderPrice.text();
        // Close mini cart
        closeMiniCart();

        return subtotal;
    }

    public void validateSubtotal(String subtotal)
    {
        // Verify the mini cart shows the specified subtotal
        // Open mini cart
        openMiniCart();
        // Verify subtotal equals specified subtotal
        // Compare the subTotal to the parameter
        subOrderPrice.shouldHave(exactText(subtotal));
        // Close Mini Cart
        closeMiniCart();
    }

    /**
     * @param position
     * @param product
     */
    public void validateMiniCart(int position, Product product)
    {
        validateMiniCart(position, product.getName(), product.getStyle(), product.getSize(), product.getAmount(), product.getTotalUnitPrice());
    }

    /**
     * @param position
     * @param product
     * @param productAmount
     * @param productTotalPrice
     */
    public void validateMiniCart(int position, Product product, int productAmount, String productTotalPrice)
    {
        validateMiniCart(position, product.getName(), product.getStyle(), product.getSize(), productAmount, productTotalPrice);
    }

    private void validateMiniCart(int position, String productName, String productStyle, String productSize, int productCount, String prodTotalPrice)
    {
        // Open the mini cart
        openMiniCart();
        // Validate data of specified item
        // Product Name
        SelenideElement miniCartItems = $("ul.cartMiniElementList li:nth-child(" + position + ") ul.cartItems");
        // Compares the name of the cart item at position @{position} to the parameter
        miniCartItems.find(".prodName").shouldHave(exactText(productName));
        // Product Style
        // Compares the style of the cart item at position @{position} to the parameter
        miniCartItems.find(".prodStyle").shouldHave(exactText(productStyle));
        // Product Size
        // Compares the style of the cart item at position @{position} to the parameter
        miniCartItems.find(".prodSize").shouldHave(exactText(productSize));
        // Amount
        // Compares the amount of the cart item at position @{position} to the parameter
        miniCartItems.find(".prodCount").shouldHave(exactText(Integer.toString(productCount)));
        // Price
        // Compares the price of the cart item at position @{position} to the parameter
        miniCartItems.find(".prodPrice").shouldHave(exactText(prodTotalPrice));
        // Close mini cart
        closeMiniCart();
    }

}