import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './table-structure.reducer';
import { ITableStructure } from 'app/shared/model/table-structure.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITableStructureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITableStructureUpdateState {
  isNew: boolean;
}

export class TableStructureUpdate extends React.Component<ITableStructureUpdateProps, ITableStructureUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { tableStructureEntity } = this.props;
      const entity = {
        ...tableStructureEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/table-structure');
  };

  render() {
    const { tableStructureEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jRiverApplicationApp.tableStructure.home.createOrEditLabel">Create or edit a TableStructure</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tableStructureEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="table-structure-id">ID</Label>
                    <AvInput id="table-structure-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bigBlindLabel" for="table-structure-bigBlind">
                    Big Blind
                  </Label>
                  <AvField id="table-structure-bigBlind" type="string" className="form-control" name="bigBlind" />
                </AvGroup>
                <AvGroup>
                  <Label id="rakePolicyLabel" for="table-structure-rakePolicy">
                    Rake Policy
                  </Label>
                  <AvField id="table-structure-rakePolicy" type="text" name="rakePolicy" />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfSeatsLabel" for="table-structure-numberOfSeats">
                    Number Of Seats
                  </Label>
                  <AvField id="table-structure-numberOfSeats" type="string" className="form-control" name="numberOfSeats" />
                </AvGroup>
                <AvGroup>
                  <Label id="minBuyInLabel" for="table-structure-minBuyIn">
                    Min Buy In
                  </Label>
                  <AvField id="table-structure-minBuyIn" type="string" className="form-control" name="minBuyIn" />
                </AvGroup>
                <AvGroup>
                  <Label id="maxBuyInLabel" for="table-structure-maxBuyIn">
                    Max Buy In
                  </Label>
                  <AvField id="table-structure-maxBuyIn" type="string" className="form-control" name="maxBuyIn" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/table-structure" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  tableStructureEntity: storeState.tableStructure.entity,
  loading: storeState.tableStructure.loading,
  updating: storeState.tableStructure.updating,
  updateSuccess: storeState.tableStructure.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableStructureUpdate);
