package com.order.order.service;
import com.inventory.inventory.dto.InventoryDTO;
import com.order.order.common.ErrorResponse;
import com.order.order.common.OrderResponse;
import com.order.order.common.SuccessOrderResponse;
import com.order.order.dao.OrderDao;
import com.order.order.dto.OrderDTO;
import com.order.order.model.Orders;
import com.product.product.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderDao orderDao, ModelMapper modelMapper) {
        this.inventoryWebClient=inventoryWebClient;
        this.productWebClient=productWebClient;
        this.orderDao = orderDao;
        this.modelMapper = modelMapper;
    }


    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = orderDao.findAll();
        return modelMapper.map(ordersList,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderResponse addOrder(OrderDTO orderDTO) {

        Integer itemId = orderDTO.getItemId();

        try {
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse != null;

            if (inventoryResponse.getQuantity() > 0) {
                if (productResponse.getForSale() == 1) {
                    orderDao.save(modelMapper.map(orderDTO, Orders.class));
                }
                else {
                    return new ErrorResponse("This item is not for sale");
                }
                return new SuccessOrderResponse(orderDTO);
            }
            else {
                return new ErrorResponse("Item not available, please try later");
            }

        }
        catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorResponse("Item not found");
            }
        }

        return null;
    }


    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderDao.save(modelMapper.map(orderDTO,Orders.class));
        return orderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderDao.deleteById(orderId);
        return "Order deleted successfully";
    }

    public OrderDTO getOrderById(Integer orderId){
        Orders order = orderDao.getOrderById(orderId);
        return modelMapper.map(order,OrderDTO.class);
    }


}
