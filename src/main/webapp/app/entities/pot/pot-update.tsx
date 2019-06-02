import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';
import { getEntities as getTableSnapshots } from 'app/entities/table-snapshot/table-snapshot.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pot.reducer';
import { IPot } from 'app/shared/model/pot.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPotUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPotUpdateState {
  isNew: boolean;
  tableSnapshotId: string;
}

export class PotUpdate extends React.Component<IPotUpdateProps, IPotUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      tableSnapshotId: '0',
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

    this.props.getTableSnapshots();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { potEntity } = this.props;
      const entity = {
        ...potEntity,
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
    this.props.history.push('/entity/pot');
  };

  render() {
    const { potEntity, tableSnapshots, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jRiverApplicationApp.pot.home.createOrEditLabel">Create or edit a Pot</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : potEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pot-id">ID</Label>
                    <AvInput id="pot-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="potChipsLabel" for="pot-potChips">
                    Pot Chips
                  </Label>
                  <AvField id="pot-potChips" type="string" className="form-control" name="potChips" />
                </AvGroup>
                <AvGroup>
                  <Label id="potPlayersSeatNumbersLabel" for="pot-potPlayersSeatNumbers">
                    Pot Players Seat Numbers
                  </Label>
                  <AvField id="pot-potPlayersSeatNumbers" type="text" name="potPlayersSeatNumbers" />
                </AvGroup>
                <AvGroup>
                  <Label for="pot-tableSnapshot">Table Snapshot</Label>
                  <AvInput id="pot-tableSnapshot" type="select" className="form-control" name="tableSnapshot.id">
                    <option value="" key="0" />
                    {tableSnapshots
                      ? tableSnapshots.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pot" replace color="info">
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
  tableSnapshots: storeState.tableSnapshot.entities,
  potEntity: storeState.pot.entity,
  loading: storeState.pot.loading,
  updating: storeState.pot.updating,
  updateSuccess: storeState.pot.updateSuccess
});

const mapDispatchToProps = {
  getTableSnapshots,
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
)(PotUpdate);
