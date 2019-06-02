import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITableStructure, defaultValue } from 'app/shared/model/table-structure.model';

export const ACTION_TYPES = {
  FETCH_TABLESTRUCTURE_LIST: 'tableStructure/FETCH_TABLESTRUCTURE_LIST',
  FETCH_TABLESTRUCTURE: 'tableStructure/FETCH_TABLESTRUCTURE',
  CREATE_TABLESTRUCTURE: 'tableStructure/CREATE_TABLESTRUCTURE',
  UPDATE_TABLESTRUCTURE: 'tableStructure/UPDATE_TABLESTRUCTURE',
  DELETE_TABLESTRUCTURE: 'tableStructure/DELETE_TABLESTRUCTURE',
  RESET: 'tableStructure/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITableStructure>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TableStructureState = Readonly<typeof initialState>;

// Reducer

export default (state: TableStructureState = initialState, action): TableStructureState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TABLESTRUCTURE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TABLESTRUCTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TABLESTRUCTURE):
    case REQUEST(ACTION_TYPES.UPDATE_TABLESTRUCTURE):
    case REQUEST(ACTION_TYPES.DELETE_TABLESTRUCTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TABLESTRUCTURE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TABLESTRUCTURE):
    case FAILURE(ACTION_TYPES.CREATE_TABLESTRUCTURE):
    case FAILURE(ACTION_TYPES.UPDATE_TABLESTRUCTURE):
    case FAILURE(ACTION_TYPES.DELETE_TABLESTRUCTURE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TABLESTRUCTURE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TABLESTRUCTURE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TABLESTRUCTURE):
    case SUCCESS(ACTION_TYPES.UPDATE_TABLESTRUCTURE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TABLESTRUCTURE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/table-structures';

// Actions

export const getEntities: ICrudGetAllAction<ITableStructure> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TABLESTRUCTURE_LIST,
  payload: axios.get<ITableStructure>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITableStructure> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TABLESTRUCTURE,
    payload: axios.get<ITableStructure>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITableStructure> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TABLESTRUCTURE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITableStructure> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TABLESTRUCTURE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITableStructure> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TABLESTRUCTURE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
