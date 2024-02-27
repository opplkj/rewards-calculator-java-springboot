# rewards-calculator-java-springboot
 
API Endpoints
Calculate Monthly Rewards

    GET /rewards/calculate/{customerId}?yearMonth=yyyy-MM

    Calculates the rewards for a given customer and month.

    Parameters:
        customerId (path variable): The ID of the customer.
        yearMonth (request parameter): The month for which rewards are calculated, formatted as yyyy-MM.

    Response: JSON map of {YearMonth: Integer} representing the month and the corresponding rewards points.
