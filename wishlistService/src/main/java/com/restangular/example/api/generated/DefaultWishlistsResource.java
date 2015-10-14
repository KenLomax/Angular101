
package com.restangular.example.api.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
* Resource class containing the custom logic. Please put your logic here!
*/
@Component("apiWishlistsResource")
@Singleton
public class DefaultWishlistsResource implements com.restangular.example.api.generated.WishlistsResource
{
	@javax.ws.rs.core.Context
	private javax.ws.rs.core.UriInfo uriInfo;

	private static int wishlistId = 0;
	private static Map<String, Wishlist> wishlists = new HashMap<String, Wishlist>(); // List, ArrayList
	
	/* GET / */
	@Override
	public Response get(final YaasAwareParameters yaasAware)
	{
		System.out.println("In get(final YaasAwareParameters yaasAware)"+ yaasAware.toString());
		return Response.ok().entity(wishlists.values().toArray()).build();
	}

	/* POST / */
	@Override
	public Response post(final YaasAwareParameters yaasAware, final Wishlist wishlist)
	{
		System.out.println("In POST(final YaasAwareParameters yaasAware)"+ yaasAware.toString());

		List<WishlistItem> wishlistItems = new ArrayList<WishlistItem>();
		wishlistItems.add( newWishlistItem( "Oh baby" )  );
		wishlist.setItems(wishlistItems );
		wishlist.setId(Integer.toString(++wishlistId));
		
		wishlists.put( wishlist.getId(), wishlist);
		
		System.out.println("In POST and returning "+ uriInfo.getAbsolutePath());

		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	private WishlistItem newWishlistItem( String product ){
		WishlistItem wli = new WishlistItem();
		wli.setProduct(product);
		return wli;
	}
	
	/* GET /{wishlistId} */
	@Override
	public Response getByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId)
	{
		System.out.println("In GET /{wishlistId} for "+ wishlistId);
		if (wishlists.containsKey(wishlistId))
			return Response.ok().entity( wishlists.get(wishlistId)).build();
		System.out.println("In GET /{wishlistId} , not wishlist found for "+ wishlistId);
		return Response.ok().entity(null).build();
	}

	/* PUT /{wishlistId} */
	@Override
	public Response putByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId, final Wishlist wishlist)
	{
		System.out.println("In PUT /{wishlistId} for "+ wishlistId);	
		wishlists.put(wishlistId, wishlist);
		// place some logic here
		return Response.ok().build();
	}

	/* DELETE /{wishlistId} */
	@Override
	public Response deleteByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId)
	{
		System.out.println("In DELETE /{wishlistId} for "+ wishlistId);

		wishlists.remove(wishlistId);
		// place some logic here
		return Response.noContent().build();
	}
	
	/* GET /{wishlistId}/wishlistItems */
	@Override
	public Response getByWishlistIdWishlistItems( YaasAwareParameters yaasAware,  String wishlistId) {
		System.out.println("In GET /{wishlistId}/wishlistItems for "+ wishlistId);
        return Response.ok().entity( wishlists.get(wishlistId).getItems() ).build();
	}
	
	/* POST /{wishlistId}/wishlistItems */
	@Override
	public Response postByWishlistIdWishlistItems(YaasAwareParameters yaasAware, String wishlistId,  WishlistItem wishlistItem) {
		System.out.println("In POST /{wishlistId}/wishlistItems for "+ wishlistId);
		wishlists.get(wishlistId).getItems().add( wishlistItem);
		return Response.created(uriInfo.getAbsolutePath()).build();
	}
}
