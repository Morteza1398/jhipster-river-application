import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITableStructure } from 'app/shared/model/table-structure.model';
import { getEntities as getTableStructures } from 'app/entities/table-structure/table-structure.reducer';
import { getEntity, updateEntity, createEntity, reset } from './table-snapshot.reducer';
import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITableSnapshotUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITableSnapshotUpdateState {
  isNew: boolean;
  structureId: string;
}

export class TableSnapshotUpdate extends React.Component<ITableSnapshotUpdateProps, ITableSnapshotUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      structureId: '0',
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

    this.props.getTableStructures();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { tableSnapshotEntity } = this.props;
      const entity = {
        ...tableSnapshotEntity,
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
    this.props.history.push('/entity/table-snapshot');
  };

  render() {
    const { tableSnapshotEntity, tableStructures, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jRiverApplicationApp.tableSnapshot.home.createOrEditLabel">Create or edit a TableSnapshot</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tableSnapshotEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="table-snapshot-id">ID</Label>
                    <AvInput id="table-snapshot-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dealerSeatNumberLabel" for="table-snapshot-dealerSeatNumber">
                    Dealer Seat Number
                  </Label>
                  <AvField id="table-snapshot-dealerSeatNumber" type="string" className="form-control" name="dealerSeatNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="stageLabel" for="table-snapshot-stage">
                    Stage
                  </Label>
                  <AvInput
                    id="table-snapshot-stage"
                    type="select"
                    className="form-control"
                    name="stage"
                    value={(!isNew && tableSnapshotEntity.stage) || 'EMPTY'}
                  >
                    <option value="EMPTY">EMPTY</option>
                    <option value="NOT_STARTED">NOT_STARTED</option>
                    <option value="PRE_FLOP">PRE_FLOP</option>
                    <option value="FLOP">FLOP</option>
                    <option value="TURN">TURN</option>
                    <option value="RIVER">RIVER</option>
                    <option value="COLLECT">COLLECT</option>
                    <option value="MOCK">MOCK</option>
                    <option value="FINISHED">FINISHED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="maxRaisedChipLabel" for="table-snapshot-maxRaisedChip">
                    Max Raised Chip
                  </Label>
                  <AvField id="table-snapshot-maxRaisedChip" type="string" className="form-control" name="maxRaisedChip" />
                </AvGroup>
                <AvGroup>
                  <Label id="turnActSeatNumberLabel" for="table-snapshot-turnActSeatNumber">
                    Turn Act Seat Number
                  </Label>
                  <AvField id="table-snapshot-turnActSeatNumber" type="string" className="form-control" name="turnActSeatNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="lastToActSeatNumberLabel" for="table-snapshot-lastToActSeatNumber">
                    Last To Act Seat Number
                  </Label>
                  <AvField id="table-snapshot-lastToActSeatNumber" type="string" className="form-control" name="lastToActSeatNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="fullRaiserSeatNumberLabel" for="table-snapshot-fullRaiserSeatNumber">
                    Full Raiser Seat Number
                  </Label>
                  <AvField id="table-snapshot-fullRaiserSeatNumber" type="string" className="form-control" name="fullRaiserSeatNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="fullRaiserChipDifferenceLabel" for="table-snapshot-fullRaiserChipDifference">
                    Full Raiser Chip Difference
                  </Label>
                  <AvField
                    id="table-snapshot-fullRaiserChipDifference"
                    type="string"
                    className="form-control"
                    name="fullRaiserChipDifference"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="normalPlayersCountLabel" for="table-snapshot-normalPlayersCount">
                    Normal Players Count
                  </Label>
                  <AvField id="table-snapshot-normalPlayersCount" type="string" className="form-control" name="normalPlayersCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="chipInStagePatternLabel" for="table-snapshot-chipInStagePattern">
                    Chip In Stage Pattern
                  </Label>
                  <AvField id="table-snapshot-chipInStagePattern" type="text" name="chipInStagePattern" />
                </AvGroup>
                <AvGroup>
                  <Label id="cardsLabel" for="table-snapshot-cards">
                    Cards
                  </Label>
                  <AvField id="table-snapshot-cards" type="text" name="cards" />
                </AvGroup>
                <AvGroup>
                  <Label for="table-snapshot-structure">Structure</Label>
                  <AvInput id="table-snapshot-structure" type="select" className="form-control" name="structure.id">
                    <option value="" key="0" />
                    {tableStructures
                      ? tableStructures.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/table-snapshot" replace color="info">
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
  tableStructures: storeState.tableStructure.entities,
  tableSnapshotEntity: storeState.tableSnapshot.entity,
  loading: storeState.tableSnapshot.loading,
  updating: storeState.tableSnapshot.updating,
  updateSuccess: storeState.tableSnapshot.updateSuccess
});

const mapDispatchToProps = {
  getTableStructures,
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
)(TableSnapshotUpdate);
