import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPot, defaultValue } from 'app/shared/model/pot.model';

export const ACTION_TYPES = {
  FETCH_POT_LIST: 'pot/FETCH_POT_LIST',
  FETCH_POT: 'pot/FETCH_POT',
  CREATE_POT: 'pot/CREATE_POT',
  UPDATE_POT: 'pot/UPDATE_POT',
  DELETE_POT: 'pot/DELETE_POT',
  RESET: 'pot/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPot>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PotState = Readonly<typeof initialState>;

// Reducer

export default (state: PotState = initialState, action): PotState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_POT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_POT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_POT):
    case REQUEST(ACTION_TYPES.UPDATE_POT):
    case REQUEST(ACTION_TYPES.DELETE_POT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_POT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_POT):
    case FAILURE(ACTION_TYPES.CREATE_POT):
    case FAILURE(ACTION_TYPES.UPDATE_POT):
    case FAILURE(ACTION_TYPES.DELETE_POT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_POT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_POT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_POT):
    case SUCCESS(ACTION_TYPES.UPDATE_POT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_POT):
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

const apiUrl = 'api/pots';

// Actions

export const getEntities: ICrudGetAllAction<IPot> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_POT_LIST,
  payload: axios.get<IPot>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPot> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_POT,
    payload: axios.get<IPot>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_POT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_POT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPot> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_POT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
