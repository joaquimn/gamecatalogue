package domain;

public class Producer {
    private String producerId;
    private String producerName;

    public Producer(String producerId, String producerName) {
        this.producerId = producerId;
        this.producerName = producerName;
    }

    public Producer(){}

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
