package com.codmind.orderapi.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E,D> {

    public abstract D fromEntity(E entity);

    public abstract E fromDTO(D dto);

    public abstract List<D> fromEntity(List<E> entitys);

    public abstract List<E> fromDTO(List<D> dtos);
}
