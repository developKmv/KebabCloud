package ru.dev.A1.A1.data;

import ru.dev.A1.A1.models.KebabOrder;

public interface OrderRepository_jdbc {
    KebabOrder save(KebabOrder kebabOrder);
}
